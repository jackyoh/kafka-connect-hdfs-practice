package idv.jack.kafka.connect

import java.util

import idv.jack.kafka.task.HDFSSinkTask
//import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.connect.connector.Task
import org.apache.kafka.connect.sink.SinkConnector

class HDFSSinkConnector extends SinkConnector{



  override def start(props: util.Map[String, String]): Unit = {
    //TODO
  }


  override def taskClass(): Class[_ <: Task] = classOf[HDFSSinkTask]

  override def version(): String = {
    "0.0.1"
  }

  override def stop(): Unit = {
    //TODO
  }

  override def taskConfigs(maxTasks: Int): util.List[util.Map[String, String]] = {
    var configs = new util.ArrayList[util.Map[String, String]]()
    var config = new util.HashMap[String, String]()
    configs.add(config)

    (0 until maxTasks).foreach { i =>
      configs.add(config)
    }
    configs
  }

  override def config(): ConfigDef = {
    new ConfigDef()
  }
}
