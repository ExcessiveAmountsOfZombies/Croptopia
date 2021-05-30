# Croptopia

Mod can be built using the gradle command line

### Fabric
`gradlew :fabric:build -Plaunch_mod=c`
### Forge
`gradlew :forge:build -Plaunch_mod=forge`

If you build fabric and then want to build forge, or the other way around, run `gradlew clean` to clear the cache out so the jar does not contain files meant for the other platform.
