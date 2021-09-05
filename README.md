# Croptopia

Mod can be built using the gradle command line

### Fabric
`gradlew :fabric:build -Plaunch_mod=c`
### Forge
- As forge is not updated to 1.17 you will have to check out the 1.16.5-Forge branch to build the mod. the master branch is just to show the current versions of the mod. 

`gradlew :forge:build -Plaunch_mod=forge`

If you build fabric and then want to build forge, or the other way around, run `gradlew clean` to clear the cache out so the jar does not contain files meant for the other platform.

Discord
https://discord.gg/GZjtAn3F6W
