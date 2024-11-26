package local
package scalajs

import sbt._

import Keys._
import sbtcrossproject.CrossPlugin.autoImport._
import sbtcrossproject.CrossProject
import scalajscrossproject.ScalaJSCrossPlugin.autoImport._

object LocalPlugin extends AutoPlugin {

  object autoImport {

    def kantanCrossProject(id: String, path: String): CrossProject =
      // crossProject(JSPlatform, JVMPlatform)
      CrossProject(id = id, file(id))(JSPlatform, JVMPlatform)
        .withoutSuffixFor(JVMPlatform)
        .crossType(CrossType.Full)
        .jsSettings(name := id + "-js")
        .jvmSettings(name := id + "-jvm")
        .in(file(path))
  }

  // import autoImport._

}
