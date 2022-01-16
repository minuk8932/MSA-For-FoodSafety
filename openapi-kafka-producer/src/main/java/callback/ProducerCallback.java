package callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerCallback implements Callback {             // for future blocking

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerCallback.class);

    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if(exception != null) {
            LOGGER.error(exception.getMessage(), exception);
        }
        else {
            LOGGER.info(metadata.toString());
        }
    }
}
