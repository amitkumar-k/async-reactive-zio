package kafka.zio.model

import zio.json._

import java.time.OffsetDateTime
import java.util.UUID

case class Booking(UUID: UUID, timestamp: OffsetDateTime, movie: String, theatre: String, details: String)

object Booking {

  implicit val encoder: JsonEncoder[Booking] =
    DeriveJsonEncoder.gen[Booking]

  implicit val decoder: JsonDecoder[Booking] =
    DeriveJsonDecoder.gen[Booking]
}