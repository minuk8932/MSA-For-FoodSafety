package partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.InvalidRecordException;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class OpenApiPartitioner implements Partitioner {

    private static final String MESSAGE_KEY = "Open-Api";

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        if(keyBytes == null) {
            throw new InvalidRecordException("Need message key");
        }

        if(key.equals(MESSAGE_KEY)) {        // return partition number
            return 0;
        }

        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();

        return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;       // hash value allocate
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
