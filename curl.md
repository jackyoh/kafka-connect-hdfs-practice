#### Create Connector API for distributed mode
```
$ curl -H "Content-Type: application/json" -X POST -d '{"name": "hdfs-sink-connector-distribute", "config": {"connector.class": "idv.jack.kafka.connect.HDFSSinkConnector", "name": "hdfstest3", "tasks.max": "1", "topics": "flight52","hdfs.url": "hdfs://rd-infra-hbase-master:9000", "hdfs.output.folder.root": "/hdfs1", "datanode-on-failure.policy": "NEVER", "hdfs.output.file.prefix": "result"}}' http://10.1.3.229:8083/connectors
```

```
$ curl -H "Content-Type: application/json" -X POST -d '{"name": "hdfs-sink-connector-distribute4", "config": {"connector.class": "io.confluent.connect.hdfs.HdfsSinkConnector", "name": "hdfstest5", "tasks.max": "2", "topics": "flight100","hdfs.url": "hdfs://hdfs:9000", "flush.size": "1000000", "rotate.interval.ms": "180000", "format.class": "io.confluent.connect.hdfs.json.JsonFormat"}}' http://10.1.3.227:8083/connectors
```

#### Delete Connector API
```
$ curl -X DELETE http://10.1.3.229:8083/connectors/hdfs-sink-connector-distribute
```

