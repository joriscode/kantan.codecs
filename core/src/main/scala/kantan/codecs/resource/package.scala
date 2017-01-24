/*
 * Copyright 2017 Nicolas Rinaudo
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

import java.io.{InputStream, OutputStream, Reader, Writer}

package object resource {
  /** [[Resource]] specialised for [[java.io.InputStream]]. */
  type InputResource[A] = Resource[A, InputStream, ResourceError]

  /** [[Resource]] specialised for [[java.io.Reader]]. */
  type ReaderResource[A] = Resource[A, Reader, ResourceError]

  /** [[Resource]] specialised for [[java.io.OutputStream]]. */
  type OutputResource[A] = Resource[A, OutputStream, ResourceError]

  /** [[Resource]] specialised for [[java.io.Writer]]. */
  type WriterResource[A] = Resource[A, Writer, ResourceError]
}
