# Reach Entity Attributes
[![Maven Repository](https://img.shields.io/maven-metadata/v/https/maven.jamieswhiteshirt.com/libs-release/com/jamieswhiteshirt/reach-entity-attributes/maven-metadata.xml.svg)](https://maven.jamieswhiteshirt.com/libs-release/com/jamieswhiteshirt/reach-entity-attributes/)

Library of entity attributes for armor, tools, potions, etc. to increase or decrease reach distance and attack range.

## Usage

To use and include this mod in your project, add the following to your `build.gradle`:

```groovy
repositories {
    maven {url "https://maven.jamieswhiteshirt.com/libs-release/"}
}

dependencies {
    modImplementation "com.jamieswhiteshirt:reach-entity-attributes:$VERSION"
    include "com.jamieswhiteshirt:reach-entity-attributes:$VERSION"
}
```

## Features

### `com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes.REACH`
Used to modify the range for breaking and interacting with blocks. 4.5 (survival) or 5.0 (creative) by default. The attribute adds to the base value.

### `com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes.ATTACK_RANGE`
Used to modify the range for attacking and interacting with entities. 3.0 (survival) or 6.0 (creative) by default. The attribute adds to the base value.

The player's reach is typically greater than their attack range. Any attack range beyond the player's reach will be ineffective.

## Developing Reach Entity Attributes

To get started, refer to the [Fabric documentation](https://fabricmc.net/wiki/tutorial:setup).
