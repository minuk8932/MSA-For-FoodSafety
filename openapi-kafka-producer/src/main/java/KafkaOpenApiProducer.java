import callback.ProducerCallback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import partitioner.OpenApiPartitioner;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaOpenApiProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOpenApiProducer.class);
    private static final String TOPIC_NAME = "Recipe-OpenApi";
    private static final String BOOTSTRAP_SERVERS = "15.164.86.196:9092";

    private static final String MESSAGE_VALUE = "recipeData";
    private static final String MESSAGE_KEY = "Open-Api";

    public static void main(String[] args) {

        Properties configs = new Properties();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, OpenApiPartitioner.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        int partitionNo = 0;
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, partitionNo, MESSAGE_KEY, MESSAGE_VALUE);

//        Future sender = producer.send(record);
//        RecordMetadata metadata = (RecordMetadata) sender.get();      blocking occurred !!

        producer.send(record, new ProducerCallback());

//        LOGGER.info(metadata.toString());
        LOGGER.info("{}", record);
        producer.flush();
        producer.close();

    }
}
