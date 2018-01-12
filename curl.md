#### Create Connector api for distributed mode
curl -H "Content-Type: application/json" -X POST -d '{"name": "hdfs-sink-connector-distribute", "config": {"connector.class": "idv.jack.kafka.connect.HDFSSinkConnector", "name": "hdfstest3", "tasks.max": "1", "topics": "flight52","hdfs.url": "hdfs://rd-infra-hbase-master:9000", "hdfs.output.folder.root": "/hdfs1", "datanode-on-failure.policy": "NEVER", "hdfs.output.file.prefix": "result"}}' http://10.1.3.229:8083/connectors

