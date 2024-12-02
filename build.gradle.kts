plugins {
    java
    alias(deps.plugins.quarkus)
    alias(deps.plugins.javafx)
}

group = "io.fouad"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(deps.libs.quarkus.bom))
    implementation(deps.libs.quarkus.arc)
    implementation(deps.libs.quarkusFx)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

javafx {
    version = deps.versions.javafxLibVersion.get()
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web")
}

val devEnvironmentVariables = emptyMap<String, String>()
val devSystemProperties = mutableMapOf<String, String>()

tasks.quarkusDev {
    doFirst {
        System.setProperty("quarkus.analytics.disabled", "true")
    }
    workingDirectory.set(project.projectDir)
    environmentVariables.putAll(devEnvironmentVariables)
    devSystemProperties.forEach { jvmArguments.add("-D${it.key}=${it.value}") }
}

configurations.matching { it.name.contains("downloadSources") }
    .configureEach {
        attributes {
            val os = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem().toFamilyName()
            val arch = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentArchitecture().name
            attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class, Usage.JAVA_RUNTIME))
            attribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE, objects.named(OperatingSystemFamily::class, os))
            attribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE, objects.named(MachineArchitecture::class, arch))
        }
    }

configurations.matching {
    it.isCanBeResolved && it.name.contains("quarkus")
}.configureEach {
    val runtimeAttributes = configurations.runtimeClasspath.get().attributes
    runtimeAttributes.keySet().forEach { key ->
        @Suppress("UNCHECKED_CAST")
        attributes.attribute(key as Attribute<Any>, runtimeAttributes.getAttribute(key) as Any)
    }
}