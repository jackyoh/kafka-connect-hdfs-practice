name := "kafka-connect-hdfs-practice"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.2"

val kafkaVersion = "1.0.0"
val hadoopVersion = "2.7.4"
retrieveManaged := true

libraryDependencies ++= Seq(
  "org.apache.kafka" % "connect-api" % kafkaVersion,
  "org.apache.kafka" % "connect-json" % kafkaVersion,
  "org.apache.kafka" %% "kafka" % kafkaVersion,
  "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion,
  "org.apache.hadoop" % "hadoop-common" % hadoopVersion
)
