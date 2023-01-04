package kafka.zio

import kafka.zio.config.KafkaMeta
import kafka.zio.model.Booking
import kafka.zio.util.BookingSerde
import org.apache.kafka.clients.producer.ProducerRecord
import zio.{Clock, Random, Schedule, ZIOAppDefault, ZLayer, durationInt}
import zio.kafka.producer.{Producer, ProducerSettings}
import zio.stream.ZStream

object KafkaProducer extends ZIOAppDefault {

  private val producer: ZLayer[Any, Throwable, Producer] =
    ZLayer.scoped(
      Producer.make(
        ProducerSettings(KafkaMeta.BOOSTRAP_SERVERS)
      )
    )

  def run = {
    ZStream
      .repeatZIO(Random.nextUUID <*> Clock.currentDateTime <*> Random.nextIntBetween(1, 9))
      .schedule(Schedule.spaced(1.second))
      .map { case (id, time, num) =>
        new ProducerRecord(
          KafkaMeta.KAFKA_TOPIC,
          time.getMinute,
          Booking(id, time, "Avatar", "PVR", s"Booking ${num} tickets")
        )
      }
      .via(Producer.produceAll(BookingSerde.key, BookingSerde.value))
      .drain
      .runDrain.provide(producer)
  }
}
