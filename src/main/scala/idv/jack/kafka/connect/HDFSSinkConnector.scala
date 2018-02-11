package idv.jack.kafka.connect

import java.util

import idv.jack.kafka.task.HDFSSinkTask
//import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.connect.connector.Task
import org.apache.kafka.connect.sink.SinkConnector

class HDFSSinkConnector extends SinkConnector{
  private val CONFIG_DEF: ConfigDef = new ConfigDef()
  private var configProperties: util.Map[String, String] = _

  override def start(props: util.Map[String, String]): Unit = {
    this.configProperties = props
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
    var taskProps = new util.HashMap[String, String]()
    taskProps.putAll(configProperties)

    (0 until maxTasks).foreach { i =>
       configs.add(taskProps)
    }
    configs
  }

  override def config(): ConfigDef = {
    CONFIG_DEF
  }
}
