package kafka.zio.util

import kafka.zio.model.Booking
import zio.ZIO
import zio.kafka.serde.Serde
import zio.json._

object BookingSerde {
  val key: Serde[Any, Int] = Serde.int

  val value: Serde[Any, Booking] = Serde.string.inmapM[Any, Booking](str =>
    ZIO.fromEither(str.fromJson[Booking])
      .mapError(new RuntimeException(_))
  )(b => ZIO.succeed(b.toJson))
}
