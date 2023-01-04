version := "0.1.0-SNAPSHOT"
scalaVersion := "2.13.10"
name := "AsyncReactiveZIO"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio"         % "2.0.5",
  "dev.zio" %% "zio-streams" % "2.0.5",
  "dev.zio" %% "zio-kafka"   % "2.0.2",
  "dev.zio" %% "zio-json"    % "0.4.2"
)