package kantan.codecs

import java.util.UUID

import kantan.codecs.laws.CodecValue._
import kantan.codecs.laws.discipline._
import kantan.codecs.strings.codecs
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.typelevel.discipline.scalatest.Discipline

class UUIDCodecTests extends FunSuite with GeneratorDrivenPropertyChecks with Discipline {
  checkAll("StringDecoder[UUID]", DecoderTests[String, UUID, Throwable, codecs.type].decoder[Int, Int])
  checkAll("StringEncoder[UUID]", EncoderTests[String, UUID, codecs.type].encoder[Int, Int])
  checkAll("StringCodec[UUID]", CodecTests[String, UUID, Throwable, codecs.type].codec[Int, Int])
}
