package kantan.codecs

import kantan.codecs.laws.discipline._
import kantan.codecs.simple._
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.typelevel.discipline.scalatest.Discipline

class OptionCodecTests  extends FunSuite with GeneratorDrivenPropertyChecks with Discipline {
  checkAll("Decoder[String, Option[Int], Boolean]", DecoderTests[String, Option[Int], Boolean, Codecs.type].decoder[Int, Int])
  checkAll("Encoder[String, Option[Int]]", EncoderTests[String, Option[Int], Codecs.type].encoder[Int, Int])
  checkAll("Codec[String, Option[Int]]", CodecTests[String, Option[Int], Boolean, Codecs.type].codec[Int, Int])
}