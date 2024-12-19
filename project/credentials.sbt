credentials += Credentials(
  "GitHub Package Registry",
  "maven.pkg.github.com",
  "_", // user is ignored
  sys.env.getOrElse("PUBLISH_TOKEN", "no PUBLISH_TOKEN env var")
)
