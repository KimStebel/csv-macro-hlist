name := "csv-macro-hlist-test"

scalaVersion := "2.10.0-M5"

version := "1.0"

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
  "com.chuusai" % "shapeless" % "1.2.3-SNAPSHOT" cross CrossVersion.full)

libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-compiler" % _)

