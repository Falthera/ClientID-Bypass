# ClientID Bypass

A Fabric client mod for Minecraft 1.21.11 that bypasses the ClientID plugin checks on Spigot servers.

## What This Mod Does

This mod sends forged plugin channel messages during the login handshake to pass the checks performed by the **ClientID Spigot plugin**. The ClientID plugin verifies that players have its companion mod installed by checking for specific plugin channel messages sent during login.

The bypass works by:
1. Registering login networking handlers for the ClientID channel
2. Sending pre-crafted responses that mimic a valid ClientID mod installation
3. Bypassing mod list and version checks that would otherwise kick players without the mod

## Building

This project uses Gradle with the Fabric Loom plugin. To build:

```bash
./gradlew build
```

The built JAR will be located in `build/libs/`.

**Build Configuration:**
- Minecraft: 1.21.11
- Yarn Mappings: 1.21.11+build.1
- Fabric Loader: 0.18.1
- Fabric API: 0.141.4+1.21.11
- Java: 21

**Mod Version:** 1.0.0

## Packets Sent to Bypass Checks

The mod sends four packets during the login phase via Fabric's `ClientLoginNetworking`:

| Channel | Payload | Purpose |
|---------|---------|---------|
| `clientid:modcheck` | `valid_checksum` | Indicates the client mod is legitimate |
| `clientid:modlist` | `clientid,sodium,lithium,phosphor,iris` | Sends a list of "installed" mods including clientid |
| `clientid:packlist` | (empty string) | Reports no resource packs |
| `clientid:clientversion` | `1.1.7` | Reports a valid ClientID mod version (expected by the server plugin) |

These packets match the format expected by the ClientID Spigot plugin, allowing players without the actual mod to connect to servers requiring it.

## Project Structure

```
src/
└── main/
    ├── java/
    │   └── com/novinitygames/clientid/
    │       └── client/          # Only these files are used by the Fabric mod
    │           ├── ClientIDBypassMod.java
    │           └── ClientIDNetworkHandler.java
    └── resources/
        └── fabric.mod.json     # Fabric mod descriptor (keep this)
```

The following Spigot-specific files are **not used** by this Fabric mod and can be deleted:
- `pom.xml` - Maven config (project uses Gradle)
- `src/main/resources/plugin.yml` - Spigot plugin descriptor
- `src/main/resources/config.yml` - Spigot configuration
- `src/main/resources/project.properties` - Unused
- `src/main/java/com/novinitygames/clientid/ClientID.java` - Spigot plugin main class
- `src/main/java/com/novinitygames/clientid/commands/` - Spigot command handlers
- `src/main/java/com/novinitygames/clientid/listeners/` - Spigot event listeners
- `src/main/java/com/novinitygames/clientid/packet/` - Server-side packet handlers
- `src/main/java/com/novinitygames/clientid/utils/Version.java` - Only used by Spigot plugin
- `src/main/java/com/novinitygames/clientid/utils/ReadHelpers.java` - Only used by Spigot plugin
- `src/main/java/com/novinitygames/clientid/utils/UpdateChecker.java` - Spigot update checker
- `src/main/java/com/novinitygames/clientid/utils/ConfigVerification.java` - Spigot config util
- `src/main/java/com/novinitygames/clientid/utils/GeyserUtils.java` - Spigot-specific util
- `src/main/java/com/novinitygames/clientid/Properties.java` - Spigot properties loader

## Note

This is a **client-side only mod** for Fabric. The original ClientID Spigot plugin (server-side) is not included in this repository and must be installed separately on the server.