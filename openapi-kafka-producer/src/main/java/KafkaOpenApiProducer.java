import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaOpenApiProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOpenApiProducer.class);
    private static final String TOPIC_NAME = "Recipe-OpenApi";
    private static final String BOOTSTRAP_SERVERS = "15.164.86.196:9092";

    public static void main(String[] args) {

        Properties configs = new Properties();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

        String messageValue = "recipeData";
        String messageKey = "Open-Api";
        int partitionNo = 0;
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, partitionNo, messageKey, messageValue);

        producer.send(record);
        LOGGER.info("{}", record);
        producer.flush();
        producer.close();

    }
}
