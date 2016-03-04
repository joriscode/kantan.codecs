package kantan.codecs

import kantan.codecs.laws.discipline._
import kantan.codecs.strings.Codecs
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.typelevel.discipline.scalatest.Discipline

class IntCodecTests extends FunSuite with GeneratorDrivenPropertyChecks with Discipline {
  checkAll("StringDecoder[Int]", DecoderTests[String, Int, Throwable, Codecs.type].decoder[Int, Int])
  checkAll("StringEncoder[Int]", EncoderTests[String, Int, Codecs.type].encoder[Int, Int])
  checkAll("StringCodec[Int]", CodecTests[String, Int, Throwable, Codecs.type].codec[Int, Int])
}