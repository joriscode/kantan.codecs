// ThisBuild / kantanProject := "codecs"
ThisBuild / startYear := Some(2016)
ThisBuild / scalaVersion := "2.13.15"

lazy val jsModules: Seq[ProjectReference] = Seq(
  catsJS,
  catsLawsJS,
  coreJS,
  enumeratumJS,
  enumeratumLawsJS,
  lawsJS,
  refinedJS,
  refinedLawsJS,
  scalazJS,
  scalazLawsJS,
  shapelessJS,
  shapelessLawsJS,
)

lazy val jvmModules: Seq[ProjectReference] = Seq(
  catsJVM,
  catsLawsJVM,
  coreJVM,
  enumeratumJVM,
  enumeratumLawsJVM,
  java8,
  java8Laws,
  lawsJVM,
  libra,
  libraLaws,
  refinedJVM,
  refinedLawsJVM,
  scalazJVM,
  scalazLawsJVM,
  shapelessJVM,
  shapelessLawsJVM,
)

// - root projects -----------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val root = Project(id = "kantan-codecs", base = file("."))
  .settings(moduleName := "root").aggregate((jsModules ++ jvmModules): _*)
  .dependsOn(
    catsJVM,
    coreJVM,
    enumeratumJVM,
    libra,
    refinedJVM,
    scalazJVM,
    shapelessJVM,
  )

// - core projects -----------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------

lazy val core = crossProject(JSPlatform, JVMPlatform).crossType(CrossType.Pure)
  .in(file("core")).enablePlugins(ScalaJSPlugin)
  .settings(moduleName := "kantan.codecs").settings(
    libraryDependencies ++= Seq(
      "org.spire-math" %%% "imp" % Versions.imp,
      "org.scalatest" %%% "scalatest" % Versions.scalatest % "test",
    ),
  )

lazy val coreJVM = core.jvm.settings(
  libraryDependencies +=
    "commons-io" % "commons-io" % Versions.commonsIo % "test",
)
lazy val coreJS = core.js

lazy val laws = crossProject(JSPlatform, JVMPlatform).crossType(CrossType.Pure)
  .in(file("laws")).enablePlugins(ScalaJSPlugin)
  .settings(moduleName := "kantan.codecs-laws").dependsOn(core).settings(
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % Versions.scalacheck,
      "org.scalatest" %%% "scalatest" % Versions.scalatest,
      "org.typelevel" %%% "discipline-scalatest" % Versions.disciplineScalatest,
    ),
  )

lazy val lawsJVM = laws.jvm
lazy val lawsJS = laws.js

// - cats projects -----------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val cats = crossProject(JSPlatform, JVMPlatform).crossType(CrossType.Pure)
  .in(file("cats")).enablePlugins(ScalaJSPlugin).in(file("cats/core"))
  .settings(moduleName := "kantan.codecs-cats").dependsOn(core).settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % Versions.cats,
      "org.scalatest" %%% "scalatest" % Versions.scalatest % "test",
    ),
  )

lazy val catsJVM = cats.jvm
lazy val catsJS = cats.js

lazy val catsLaws = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("cats-laws")).enablePlugins(ScalaJSPlugin)
  .in(file("cats/laws")).settings(moduleName := "kantan.codecs-cats-laws")
  .dependsOn(core, laws, cats).settings(
    libraryDependencies += "org.typelevel" %%% "cats-laws" % Versions.cats,
  )

lazy val catsLawsJVM = catsLaws.jvm
lazy val catsLawsJS = catsLaws.js

// - java8 projects ----------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val java8 = Project(id = "java8", base = file("java8/core"))
  .settings(moduleName := "kantan.codecs-java8", name := "java8")
  .dependsOn(coreJVM).settings(
    libraryDependencies ++=
      Seq("org.scalatest" %% "scalatest" % Versions.scalatest % "test"),
  )

lazy val java8Laws = Project(id = "java8-laws", base = file("java8/laws"))
  .settings(moduleName := "kantan.codecs-java8-laws", name := "java8-laws")
  .dependsOn(coreJVM, lawsJVM, java8).settings(
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-collection-compat" %
        Versions.collectionCompat,
    ),
  )

// - scalaz projects ---------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val scalaz = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("scalaz")).enablePlugins(ScalaJSPlugin)
  .in(file("scalaz/core")).settings(moduleName := "kantan.codecs-scalaz")
  .dependsOn(core).settings(
    libraryDependencies ++= Seq(
      "org.scalaz" %%% "scalaz-core" % Versions.scalaz,
      "org.scalatest" %%% "scalatest" % Versions.scalatest % "test",
    ),
  )

lazy val scalazJVM = scalaz.jvm
lazy val scalazJS = scalaz.js

lazy val scalazLaws = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("scalaz-laws"))
  .enablePlugins(ScalaJSPlugin).in(file("scalaz/laws"))
  .settings(moduleName := "kantan.codecs-scalaz-laws")
  .dependsOn(core, laws, scalaz).settings(
    libraryDependencies ++= Seq(
      "org.scalaz" %%% "scalaz-core" % Versions.scalaz,
      "org.scalaz" %%% "scalaz-scalacheck-binding" % (Versions.scalaz),
      "org.scalatest" %%% "scalatest" % Versions.scalatest % "optional",
    ),
  )

lazy val scalazLawsJVM = scalazLaws.jvm
lazy val scalazLawsJS = scalazLaws.js

// - refined project ---------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val refined = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("refined")).enablePlugins(ScalaJSPlugin)
  .in(file("refined/core")).settings(moduleName := "kantan.codecs-refined")
  .dependsOn(core).settings(
    libraryDependencies ++= Seq(
      "eu.timepit" %%% "refined" % Versions.refined,
      "org.scalatest" %%% "scalatest" % Versions.scalatest % "test",
    ),
  )

lazy val refinedJVM = refined.jvm
lazy val refinedJS = refined.js

lazy val refinedLaws = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("refined-laws"))
  .enablePlugins(ScalaJSPlugin).in(file("refined/laws"))
  .settings(moduleName := "kantan.codecs-refined-laws").settings(
    libraryDependencies +=
      "eu.timepit" %%% "refined-scalacheck" % Versions.refined,
  ).dependsOn(core, laws, refined)

lazy val refinedLawsJVM = refinedLaws.jvm
lazy val refinedLawsJS = refinedLaws.js

// - enumeratum project ------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val enumeratum = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("enumeratum")).enablePlugins(ScalaJSPlugin)
  .in(file("enumeratum/core"))
  .settings(moduleName := "kantan.codecs-enumeratum").dependsOn(core).settings(
    libraryDependencies ++= Seq(
      "com.beachape" %%% "enumeratum" % Versions.enumeratum,
      "org.scalatest" %%% "scalatest" % Versions.scalatest % "test",
    ),
  )

lazy val enumeratumJVM = enumeratum.jvm
lazy val enumeratumJS = enumeratum.js

lazy val enumeratumLaws = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("enumeratum-laws"))
  .enablePlugins(ScalaJSPlugin).in(file("enumeratum/laws"))
  .settings(moduleName := "kantan.codecs-enumeratum-laws").settings(
    libraryDependencies +=
      "com.beachape" %%% "enumeratum-scalacheck" % Versions.enumeratumScalacheck,
  ).dependsOn(core, laws, enumeratum)

lazy val enumeratumLawsJVM = enumeratumLaws.jvm
lazy val enumeratumLawsJS = enumeratumLaws.js

// - libra projects ------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val libra = Project(id = "libra", base = file("libra/core"))
  .settings(moduleName := "kantan.codecs-libra", name := "libra")
  .dependsOn(coreJVM).settings(
    libraryDependencies ++= Seq(
      "com.github.to-ithaca" %% "libra" % Versions.libra,
      "org.scalatest" %% "scalatest" % Versions.scalatest % "test",
    ),
  )

lazy val libraLaws = Project(id = "libra-laws", base = file("libra/laws"))
  .settings(moduleName := "kantan.codecs-libra-laws", name := "libra-laws")
  .dependsOn(coreJVM, lawsJVM, libra)

// - shapeless projects ------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------
lazy val shapeless = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("shapeless")).enablePlugins(ScalaJSPlugin)
  .in(file("shapeless/core")).settings(moduleName := "kantan.codecs-shapeless")
  .dependsOn(core).settings(
    libraryDependencies ++= Seq(
      "com.chuusai" %%% "shapeless" % Versions.shapeless,
      "org.scalatest" %%% "scalatest" % Versions.scalatest % "test",
    ),
  )

lazy val shapelessJVM = shapeless.jvm
lazy val shapelessJS = shapeless.js

lazy val shapelessLaws = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure).in(file("shapeless-laws"))
  .enablePlugins(ScalaJSPlugin).in(file("shapeless/laws"))
  .settings(moduleName := "kantan.codecs-shapeless-laws").settings(
    libraryDependencies +=
      "com.github.alexarchambault" %%% "scalacheck-shapeless_1.14" %
        Versions.scalacheckShapeless,
  ).dependsOn(core, laws, shapeless)

lazy val shapelessLawsJVM = shapelessLaws.jvm
lazy val shapelessLawsJS = shapelessLaws.js
