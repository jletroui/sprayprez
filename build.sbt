scalaVersion        := "2.10.3"

resolvers           ++= Seq(
  "Spray repo"    at "http://repo.spray.io",
  "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "io.spray"          %  "spray-can"            % "1.2-RC4",
  "io.spray"          %  "spray-routing"        % "1.2-RC4",
  "io.spray"          %  "spray-caching"        % "1.2-RC4",
  "io.spray"          %% "spray-json"           % "1.2.5",
  "io.spray"          %  "spray-httpx"          % "1.2-RC4",
  "io.spray"          %  "spray-client"         % "1.2-RC4",
  "com.typesafe.akka" %% "akka-actor"           % "2.2.3",
  "org.specs2"        %% "specs2"               % "1.14"          % "test",
  "io.spray"          %  "spray-testkit"        % "1.1-RC2"       % "test"
)
