# Reach Entity Attributes
[![Maven Repository](https://img.shields.io/maven-metadata/v/https/maven.jamieswhiteshirt.com/libs-release/com/jamieswhiteshirt/reach-entity-attributes/maven-metadata.xml.svg)](https://maven.jamieswhiteshirt.com/libs-release/com/jamieswhiteshirt/reach-entity-attributes/)

**A library mod for Minecraft that implements entity attributes for reach distance and attack range**

## Usage

### Setup
To use and include this mod in your project, add the following to your buildscript:

```groovy
repositories {
    maven {
        url "https://maven.jamieswhiteshirt.com/libs-release"
        content {
            includeGroup "com.jamieswhiteshirt"
        }
    }
}

dependencies {
    include modImplementation("com.jamieswhiteshirt:reach-entity-attributes:$VERSION")
}
```

### API

### `com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes.REACH`
Represents the reach distance modifier of an entity that is added to the base value. In vanilla, the base values are 4.5 for survival and 5.0 for creative.

### `com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes.ATTACK_RANGE`
Represents the attack range modifier of an entity that is added to the base value. In vanilla, the base values are 3.0 for survival and 6.0 for creative.

A player's reach is typically greater than their attack range. Any attack range beyond the player's reach will be ineffective.

---
## Development

To get started, refer to the [Fabric documentation](https://fabricmc.net/wiki/tutorial:setup).
