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
package java8

import java.time._
import java.time.format.DateTimeFormatter
import strings.StringDecoder

/** Provides useful methods for a java8 time decoder companions.
  *
  * Usage note: when declaring default implicit instances, be sure to wrap them in an [[export.Exported]]. Otherwise,
  * custom instances and default ones are very likely to conflict.
  */
trait TimeDecoderCompanion[E, F, T] {

  def decoderFrom[D](d: StringDecoder[D]): Decoder[E, D, F, T]

  // - LocalTime -------------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localTimeDecoder("HH:mm:ss.SSS")
    *      |   .decode("12:00:00.000")
    * res1: Either[DecodeError, LocalTime] = Right(12:00)
    * }}}
    */
  def localTimeDecoder(format: String): Decoder[E, LocalTime, F, T] = localTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._, format._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localTimeDecoder(DateTimeFormatter.ISO_LOCAL_TIME)
    *      |   .decode("12:00:00.000")
    * res1: Either[DecodeError, LocalTime] = Right(12:00)
    * }}}
    */
  def localTimeDecoder(format: ⇒ DateTimeFormatter): Decoder[E, LocalTime, F, T] = localTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localTimeDecoder(Format("HH:mm:ss.SSS"))
    *      |   .decode("12:00:00.000")
    * res1: Either[DecodeError, LocalTime] = Right(12:00)
    * }}}
    */
  def localTimeDecoder(format: Format): Decoder[E, LocalTime, F, T] =
    decoderFrom(StringDecoder.from(StringDecoder.makeSafe("LocalTime")(s ⇒ format.parseLocalTime(s))))

  /** Creates a [[Decoder]] instance using the [[Format.defaultLocalTimeFormat default format]].
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.defaultLocalTimeDecoder
    *      |   .decode("12:00:00.000")
    * res1: Either[DecodeError, LocalTime] = Right(12:00)
    * }}}
    */
  def defaultLocalTimeDecoder: Decoder[E, LocalTime, F, T] = localTimeDecoder(Format.defaultLocalTimeFormat)

  // - LocalDate -------------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localDateDecoder("yyyy-MM-DD")
    *      |   .decode("2000-01-01")
    * res1: Either[DecodeError, LocalDate] = Right(2000-01-01)
    * }}}
    */
  def localDateDecoder(format: String): Decoder[E, LocalDate, F, T] = localDateDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._, format._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localDateDecoder(DateTimeFormatter.ISO_LOCAL_DATE)
    *      |   .decode("2000-01-01")
    * res1: Either[DecodeError, LocalDate] = Right(2000-01-01)
    * }}}
    */
  def localDateDecoder(format: ⇒ DateTimeFormatter): Decoder[E, LocalDate, F, T] = localDateDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localDateDecoder(Format("yyyy-MM-DD"))
    *      |   .decode("2000-01-01")
    * res1: Either[DecodeError, LocalDate] = Right(2000-01-01)
    * }}}
    */
  def localDateDecoder(format: Format): Decoder[E, LocalDate, F, T] =
    decoderFrom(StringDecoder.from(StringDecoder.makeSafe("LocalDate")(s ⇒ format.parseLocalDate(s))))

  /** Creates a [[Decoder]] instance using the [[Format.defaultLocalDateFormat default format]].
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.defaultLocalDateDecoder
    *      |   .decode("2000-01-01")
    * res1: Either[DecodeError, LocalDate] = Right(2000-01-01)
    * }}}
    */
  def defaultLocalDateDecoder: Decoder[E, LocalDate, F, T] = localDateDecoder(Format.defaultLocalDateFormat)

  // - LocalDateTime ---------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localDateTimeDecoder("yyyy-MM-DD'T'HH:mm:ss.SSS")
    *      |   .decode("2000-01-01T12:00:00.000")
    * res1: Either[DecodeError, LocalDateTime] = Right(2000-01-01T12:00)
    * }}}
    */
  def localDateTimeDecoder(format: String): Decoder[E, LocalDateTime, F, T] = localDateTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._, format._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localDateTimeDecoder(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    *      |   .decode("2000-01-01T12:00:00.000")
    * res1: Either[DecodeError, LocalDateTime] = Right(2000-01-01T12:00)
    * }}}
    */
  def localDateTimeDecoder(format: ⇒ DateTimeFormatter): Decoder[E, LocalDateTime, F, T] =
    localDateTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.localDateTimeDecoder(Format("yyyy-MM-DD'T'HH:mm:ss.SSS"))
    *      |   .decode("2000-01-01T12:00:00.000")
    * res1: Either[DecodeError, LocalDateTime] = Right(2000-01-01T12:00)
    * }}}
    */
  def localDateTimeDecoder(format: Format): Decoder[E, LocalDateTime, F, T] =
    decoderFrom(StringDecoder.from(StringDecoder.makeSafe("LocalDateTime")(s ⇒ format.parseLocalDateTime(s))))

  /** Creates a [[Decoder]] instance using the [[Format.defaultLocalDateTimeFormat default format]].
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.defaultLocalDateTimeDecoder
    *      |   .decode("2000-01-01T12:00:00.000")
    * res1: Either[DecodeError, LocalDateTime] = Right(2000-01-01T12:00)
    * }}}
    */
  def defaultLocalDateTimeDecoder: Decoder[E, LocalDateTime, F, T] =
    localDateTimeDecoder(Format.defaultLocalDateTimeFormat)

  // - OffsetDateTime --------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.offsetDateTimeDecoder("yyyy-MM-DD'T'HH:mm:ss.SSSXX")
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, OffsetDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def offsetDateTimeDecoder(format: String): Decoder[E, OffsetDateTime, F, T] = offsetDateTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._, format._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.offsetDateTimeDecoder(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, OffsetDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def offsetDateTimeDecoder(format: ⇒ DateTimeFormatter): Decoder[E, OffsetDateTime, F, T] =
    offsetDateTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.offsetDateTimeDecoder(Format("yyyy-MM-DD'T'HH:mm:ss.SSSXX"))
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, OffsetDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def offsetDateTimeDecoder(format: Format): Decoder[E, OffsetDateTime, F, T] =
    decoderFrom(StringDecoder.from(StringDecoder.makeSafe("OffsetDateTime")(s ⇒ format.parseOffsetDateTime(s))))

  /** Creates a [[Decoder]] instance using the [[Format.defaultOffsetDateTimeFormat default format]].
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.defaultOffsetDateTimeDecoder
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, OffsetDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def defaultOffsetDateTimeDecoder: Decoder[E, OffsetDateTime, F, T] =
    offsetDateTimeDecoder(Format.defaultOffsetDateTimeFormat)

  // - ZonedDateTime ---------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.zonedDateTimeDecoder("yyyy-MM-DD'T'HH:mm:ss.SSSzz")
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, ZonedDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def zonedDateTimeDecoder(format: String): Decoder[E, ZonedDateTime, F, T] = zonedDateTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._, format._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.zonedDateTimeDecoder(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, ZonedDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def zonedDateTimeDecoder(format: ⇒ DateTimeFormatter): Decoder[E, ZonedDateTime, F, T] =
    zonedDateTimeDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.zonedDateTimeDecoder(Format("yyyy-MM-DD'T'HH:mm:ss.SSSzz"))
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, ZonedDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def zonedDateTimeDecoder(format: Format): Decoder[E, ZonedDateTime, F, T] =
    decoderFrom(StringDecoder.from(StringDecoder.makeSafe("ZonedDateTime")(s ⇒ format.parseZonedDateTime(s))))

  /** Creates a [[Decoder]] instance using the [[Format.defaultZonedDateTimeFormat default format]].
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.defaultZonedDateTimeDecoder
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, ZonedDateTime] = Right(2000-01-01T12:00Z)
    * }}}
    */
  def defaultZonedDateTimeDecoder: Decoder[E, ZonedDateTime, F, T] =
    zonedDateTimeDecoder(Format.defaultZonedDateTimeFormat)

  // - Instant ---------------------------------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._, format._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.instantDecoder(DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC))
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, Instant] = Right(2000-01-01T12:00:00Z)
    * }}}
    */
  def instantDecoder(format: ⇒ DateTimeFormatter): Decoder[E, Instant, F, T] = instantDecoder(Format(format))

  /** Creates a [[Decoder]] instance that uses the specified format.
    *
    * @example
    * {{{
    * scala> import java.time._, format._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.instantDecoder(Format(DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC)))
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, Instant] = Right(2000-01-01T12:00:00Z)
    * }}}
    */
  def instantDecoder(format: Format): Decoder[E, Instant, F, T] =
    decoderFrom(StringDecoder.from(StringDecoder.makeSafe("Instant")(s ⇒ format.parseInstant(s))))

  /** Creates a [[Decoder]] instance using the [[Format.defaultInstantFormat default format]].
    *
    * @example
    * {{{
    * scala> import java.time._
    * scala> import kantan.codecs.strings._
    *
    * scala> object Foo extends TimeDecoderCompanion[String, DecodeError, codecs.type] {
    *      |   override def decoderFrom[D](d: StringDecoder[D]) = d
    *      | }
    *
    * scala> Foo.defaultInstantDecoder
    *      |   .decode("2000-01-01T12:00:00.000Z")
    * res1: Either[DecodeError, Instant] = Right(2000-01-01T12:00:00Z)
    * }}}
    */
  def defaultInstantDecoder: Decoder[E, Instant, F, T] = instantDecoder(Format.defaultInstantFormat)

}
