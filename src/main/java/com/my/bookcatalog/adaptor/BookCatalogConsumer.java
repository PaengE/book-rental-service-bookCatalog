package com.my.bookcatalog.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.bookcatalog.config.KafkaProperties;
import com.my.bookcatalog.domain.BookChanged;
import com.my.bookcatalog.repository.BookCatalogRepository;
import com.my.bookcatalog.service.BookCatalogService;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookCatalogConsumer {

    private final Logger log = LoggerFactory.getLogger(BookCatalogConsumer.class);

    private final AtomicBoolean closed = new AtomicBoolean(false);

    public static final String TOPIC = "topic_catalog";

    private final KafkaProperties kafkaProperties;

    private KafkaConsumer<String, String> kafkaConsumer;

    private BookCatalogRepository bookCatalogRepository;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final BookCatalogService bookCatalogService;

    public BookCatalogConsumer(
        KafkaProperties kafkaProperties,
        BookCatalogRepository bookCatalogRepository,
        BookCatalogService bookCatalogService
    ) {
        this.kafkaProperties = kafkaProperties;
        this.bookCatalogRepository = bookCatalogRepository;
        this.bookCatalogService = bookCatalogService;
    }

    @PostConstruct
    public void start() {
        log.info("Kafka consumer starting ...");
        this.kafkaConsumer = new KafkaConsumer<>(kafkaProperties.getConsumerProps());
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        // 토픽 구독
        kafkaConsumer.subscribe(Collections.singleton(TOPIC));
        log.info("Kafka consumer started");

        executorService.execute(
            () -> {
                try {
                    while (!closed.get()) {
                        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(3));
                        for (ConsumerRecord<String, String> record : records) {
                            log.info("Consumed message in {} : {}", TOPIC, record.value());
                            ObjectMapper objectMapper = new ObjectMapper();
                            // 도서정보 변경됨 도메인 이벤트 생성
                            BookChanged bookChanged = objectMapper.readValue(record.value(), BookChanged.class);
                            // 도서 카탈로그 변경 프로세스 발행
                            bookCatalogService.processCatalogChanged(bookChanged);
                        }
                    }
                    kafkaConsumer.commitSync();
                } catch (WakeupException e) {
                    if (!closed.get()) {
                        throw e;
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
                    log.info("kafka consumer close");
                    kafkaConsumer.close();
                }
            }
        );
    }

    public KafkaConsumer<String, String> getKafkaConsumer() {
        return kafkaConsumer;
    }

    public void shutdown() {
        log.info("Shutdown Kafka consumer");
        closed.set(true);
        kafkaConsumer.wakeup();
    }
}
