version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.10"
name := "AsyncReactiveZIO"
lazy val zioVersion = "2.0.5"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio"         % zioVersion,
  "dev.zio" %% "zio-streams" % zioVersion,
  "dev.zio" %% "zio-kafka"   % "2.0.2",
  "dev.zio" %% "zio-json"    % "0.4.2"
  "dev.zio" %% "zio-test"    % zioVersion
)
