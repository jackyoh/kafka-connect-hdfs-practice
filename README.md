# kafka-connect-hdfs-practice
Create HDFS Sink Connector Command
```
$ curl -H "Content-Type: application/json" -X POST -d '{"name": "hdfs-sink-connector-datatest13", "config": {"logs.dir": "/log123","topics.dir": "/data", "connector.class": "com.island.connect.hdfs.HdfsSinkConnector", "name": "hdfs-sink-connector-datatest13", "tasks.max": "3", "topics": "datatest42","hdfs.url": "hdfs://hdfs-server-1:9000", "flush.size": "1000", "rotate.interval.ms": "120000", "format.class": "com.island.connect.hdfs.text.TextFormat", "hbase.zookeeper.quorum": "od2dev1", "hbase.zookeeper.property.clientPort": "2181", "hbase.table.name": "table6", "hbase.columnfamily": "cf"}}' http://10.1.3.231:8083/connectors
```

Delete HDFS Sink Connector Command
```
$ curl -H "Content-Type: application/json" -X DELETE http://10.1.3.231:8083/connectors/hdfs-sink-connector-datatest13
```
