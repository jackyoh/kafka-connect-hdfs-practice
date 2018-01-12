package idv.jack.kafka.task

import java.util
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}


class HDFSSinkTask extends SinkTask {
  val folderPathString = "/hdfs"
  var rowCount: Long = 0
  var fs: FileSystem = _
  var file: FSDataOutputStream = _

  override def open(partitions: util.Collection[TopicPartition]): Unit = {
    import scala.collection.JavaConversions._
    var config = new Configuration()
    config.set("fs.default.name", "hdfs://hdfs:9000")
    config.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER")
    fs = FileSystem.get(config)

    var folderPath = new Path(folderPathString)
    if(!fs.exists(folderPath)){
      fs.mkdirs(folderPath)
    }
    partitions.foreach(x => {
      val partitionID = x.partition()
      val outputFileName = s"testresult-$partitionID.txt"
      if (!fs.exists(new Path(s"$folderPathString/$outputFileName"))) {
        fs.createNewFile(new Path(s"$folderPathString/$outputFileName"))
      }
      file = fs.append(new Path(s"$folderPathString/$outputFileName"))
    })

    partitions.foreach(x => {
      context.offset(x, 0)
      context.requestCommit()
    })
    initialize(context)
  }

  override def close(partitions: util.Collection[TopicPartition]): Unit = {
    if(file != null){
      file.close()
    }

    if(fs != null){
      fs.close()
    }
  }

  override def start(props: util.Map[String, String]): Unit = {

  }

  override def stop(): Unit = {

  }

  override def put(records: util.Collection[SinkRecord]): Unit = {
    import scala.collection.JavaConversions._
    records.foreach(x => {
        file.writeChars(x.value().toString() + "\n")
        rowCount = rowCount + 1
    })
  }

  override def version(): String = {
    "0.0.1"
  }

  override def flush(offsets: util.Map[TopicPartition, OffsetAndMetadata] ): Unit = {
    if (file != null){
      file.hflush()
    }
  }
}
