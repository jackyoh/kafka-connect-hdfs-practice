package idv.jack.kafka.task

import java.util

import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.connect.sink.{SinkRecord, SinkTaskContext}

class DataWriter(context: SinkTaskContext) {

  private var topicPartitions = new util.HashMap[Integer, TopicPartitionWriter]

  def open(partitions: util.Collection[TopicPartition]): Unit = {
    import scala.collection.JavaConversions._
    partitions.foreach(topicPartition => {
      val topicPartitionWriter = new TopicPartitionWriter(context, topicPartition)
      topicPartitionWriter.createFolder()
      topicPartitions.put(topicPartition.partition, topicPartitionWriter)
    })
  }

  def writer(records: util.Collection[SinkRecord]): Unit = {
    import scala.collection.JavaConversions._
    records.foreach(record => {
      topicPartitions.get(record.kafkaPartition()).writerToBuffer(record)
    })
  }

  def flush(currentOffsets: util.Map[TopicPartition, OffsetAndMetadata]): Unit = {
    import scala.collection.JavaConversions._
    currentOffsets.foreach(kv => {
      val topicPartition = kv._1
      topicPartitions.get(topicPartition.partition()).flushDataToHDFS()
      topicPartitions.get(topicPartition).flushOffsetToHDFS(kv._2)
    })
  }

  def close(): Unit ={
    import scala.collection.JavaConversions._
    topicPartitions.foreach(kv => {
      val topicPartitionWriter = kv._2
      topicPartitionWriter.close()
    })
  }
}
