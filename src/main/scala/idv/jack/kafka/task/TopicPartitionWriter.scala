package idv.jack.kafka.task

import java.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.connect.sink.{SinkRecord, SinkTaskContext}

class TopicPartitionWriter(context: SinkTaskContext, topicPartition: TopicPartition) {

  private val buffers = new util.ArrayList[String]()

  //TODO IS hard code
  var config = new Configuration()
  config.set("fs.default.name", "hdfs://host1:9000")
  config.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER")

  var fs = FileSystem.get(config)
  val outputDataFilePath = "hdfs://host1:9000/topics_test/" +
                                          topicPartition.topic() + "/" +
                                          "partition=" + topicPartition.partition()


  val outputOffsetFilePath = "hdfs://host1:9000/topics_test/" +
                                           topicPartition.topic() + "/" +
                                           "offset"


  def createFolder(): Unit = {
    if (!fs.exists(new Path(outputDataFilePath))) {
       fs.mkdirs(new Path(outputDataFilePath))
    }

    if (!fs.exists(new Path(outputOffsetFilePath))) {
       fs.mkdirs(new Path(outputOffsetFilePath))
    }
  }

  def writerToBuffer(sinkRecord: SinkRecord): Unit = {
    val dataValue = sinkRecord.value().toString()
    buffers.add(dataValue)
  }

  def flushDataToHDFS(): Unit = {
    import scala.collection.JavaConversions._
    buffers.foreach(bufferValue => {
       //TODO write data to HDFS
    })
  }

  def flushOffsetToHDFS(offsetMetaData: OffsetAndMetadata): Unit = {
     //TODO write offset value to HDFS
  }

  def close(): Unit = {

  }

}
