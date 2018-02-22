sbtPlugin := true

organization := "com.evolutiongaming"
name := "sbt-git-stamp"

description := "An SBT plugin that will stamp the MANIFEST.MF file in the output artifact with some basic git information."

homepage := Some(url("https://github.com/evolution-gaming/sbt-git-stamp"))

organizationName := "Evolution Gaming"
organizationHomepage := Some(url("https://www.evolutiongaming.com/"))
bintrayOrganization := Some("evolutiongaming")

resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

startYear := Some(2018)

crossSbtVersions := Seq("1.1.1", "0.13.16")

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= Seq (
  "org.eclipse.jgit" % "org.eclipse.jgit" % "4.10.0.201712302008-r"
)

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  releaseStepCommandAndRemaining("^ test"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("^ publish"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
