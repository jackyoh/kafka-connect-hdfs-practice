package idv.jack.kafka.task

import java.util
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}


class HDFSSinkTask extends SinkTask {
  private var configProperties: util.Map[String, String] = _
  private var dataWriter: DataWriter = _

  override def start(props: util.Map[String, String]) = {
    configProperties = props
    dataWriter = new DataWriter(context)
  }

  override def open(partitions: util.Collection[TopicPartition]): Unit = {
    dataWriter.open(partitions)
  }

  override def put(records: util.Collection[SinkRecord]) = {
    dataWriter.writer(records)
  }

  override def close(partitions: util.Collection[TopicPartition]): Unit = {
    dataWriter.close()
  }

  override def flush(currentOffsets: util.Map[TopicPartition, OffsetAndMetadata]): Unit ={
     dataWriter.flush(currentOffsets)
  }

  override def stop() = {

  }

  override def version() = {
    "0.0.1"
  }
}
