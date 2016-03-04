package kantan.codecs

import kantan.codecs.laws.discipline._
import kantan.codecs.strings.Codecs
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.typelevel.discipline.scalatest.Discipline

class ShortCodecTests extends FunSuite with GeneratorDrivenPropertyChecks with Discipline {
  checkAll("StringDecoder[Short]", DecoderTests[String, Short, Throwable, Codecs.type].decoder[Int, Int])
  checkAll("StringEncoder[Short]", EncoderTests[String, Short, Codecs.type].encoder[Int, Int])
  checkAll("StringCodec[Short]", CodecTests[String, Short, Throwable, Codecs.type].codec[Int, Int])
}