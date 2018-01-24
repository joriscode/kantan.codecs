/*
 * Copyright 2016 Nicolas Rinaudo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kantan.codecs
package strings
package joda
package time

import laws.discipline._, arbitrary._
import org.joda.time.LocalDateTime

class LocalDateTimeCodecTests extends DisciplineSuite {

  checkAll("StringDecoder[LocalDateTime]", StringDecoderTests[LocalDateTime].decoder[Int, Int])
  checkAll("StringDecoder[LocalDateTime]", SerializableTests[StringEncoder[LocalDateTime]].serializable)

  checkAll("StringEncoder[LocalDateTime]", StringEncoderTests[LocalDateTime].encoder[Int, Int])
  checkAll("StringEncoder[LocalDateTime]", SerializableTests[StringEncoder[LocalDateTime]].serializable)

  checkAll("StringCodec[LocalDateTime]", StringCodecTests[LocalDateTime].codec[Int, Int])

}