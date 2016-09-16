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

package kantan.codecs.strings

import kantan.codecs.Encoder

object StringEncoder {
  /** Summons an implicit instance of `StringEncoder[D]` if one can be found, fails compilation otherwise. */
  def apply[D](implicit ev: StringEncoder[D]): StringEncoder[D] = macro imp.summon[StringEncoder[D]]

  @deprecated("use from instead (see https://github.com/nrinaudo/kantan.codecs/issues/22)", "0.1.8")
  def apply[D](f: D ⇒ String): StringEncoder[D] = from(f)
  def from[D](f: D ⇒ String): StringEncoder[D] = Encoder.from(f)
}

/** Defines default instances of [[StringEncoder]]. */
trait StringEncoderInstances
