package kafka.zio

import kafka.zio.config.KafkaMeta
import kafka.zio.util.BookingSerde
import zio.{Console, ZIOAppDefault, ZLayer}
import zio.kafka.consumer.{Consumer, ConsumerSettings, Subscription}
import zio.stream.ZStream

object KafkaConsumer extends ZIOAppDefault {

  private val consumerLayer: ZLayer[Any, Throwable, Consumer] =
    ZLayer.scoped(
      Consumer.make(
        ConsumerSettings(KafkaMeta.BOOSTRAP_SERVERS)
          .withGroupId(KafkaMeta.CONSUMER_GROUP)
      )
    )

  def run = {
    val consumer: ZStream[Consumer, Throwable, Nothing] =
      Consumer
        .subscribeAnd(Subscription.topics(KafkaMeta.KAFKA_TOPIC))
        .plainStream(BookingSerde.key, BookingSerde.value)
        .tap(comRec => Console.printLine(comRec.value))
        .map(_.offset)
        .aggregateAsync(Consumer.offsetBatches)
        .mapZIO(_.commit)
        .drain

    consumer.runDrain.provide(consumerLayer)
  }
}
