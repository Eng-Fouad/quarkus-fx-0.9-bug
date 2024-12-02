dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            version("javafxPluginVersion", "0.1.0") // https://plugins.gradle.org/plugin/org.openjfx.javafxplugin
            version("quarkusLibVersion", "3.17.2") // https://central.sonatype.com/artifact/io.quarkus/quarkus-bom
            version("javafxLibVersion", "23.0.1") // https://central.sonatype.com/artifact/org.openjfx/javafx
            version("quarkusFxLibVersion", "0.9.0") // https://central.sonatype.com/artifact/io.quarkiverse.fx/quarkus-fx

            plugin("javafx", "org.openjfx.javafxplugin").versionRef("javafxPluginVersion")
            plugin("quarkus", "io.quarkus").versionRef("quarkusLibVersion")

            library("libs.quarkus.bom", "io.quarkus", "quarkus-bom").versionRef("quarkusLibVersion")
            library("libs.quarkus.arc", "io.quarkus", "quarkus-arc").versionRef("quarkusLibVersion")
            library("libs.quarkusFx", "io.quarkiverse.fx", "quarkus-fx").versionRef("quarkusFxLibVersion")
        }
    }
}

rootProject.name = "quarkus-fx-0.9-bug"