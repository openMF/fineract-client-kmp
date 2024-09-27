Releasing
=========

1. Change the version in `gradle.properties` to a non-SNAPSHOT version.
2. `git commit -am "Prepare for release X.Y.Z."` (where X.Y.Z is the new version)
3. `git tag -a X.Y.Z -m "Version X.Y.Z"` (where X.Y.Z is the new version).
4. Update `gradle.properties` to the next SNAPSHOT version.
5. `git commit -am "Prepare next development version."`.
6. `git push && git push --tags`.

This will trigger a GitHub Action workflow which will create a GitHub release and upload the
release artifacts to [Maven Central][maven-central].

[maven-central]: https://repo.maven.apache.org/maven2/org/openMF/fineract-client-cmp/
```