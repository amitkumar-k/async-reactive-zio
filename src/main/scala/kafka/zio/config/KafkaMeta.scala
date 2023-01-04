package kafka.zio.config

object KafkaMeta {
  val BOOSTRAP_SERVERS = List("localhost:9092")
  val KAFKA_TOPIC      = "updates-consumer"
  val CONSUMER_GROUP   = "streaming-consumer-group"
}
