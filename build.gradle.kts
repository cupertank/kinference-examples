plugins {
    kotlin("multiplatform") version "1.6.20"
    application
}

group = "me.cupertank"
version = "1.0.0"

repositories {
    mavenCentral()
    maven(url = "https://packages.jetbrains.team/maven/p/ki/maven")
}

application {
    mainClass.set("me.cupertank.MainKt")
}

kotlin {
    jvm() {
        withJava()
    }

    js(IR) { // Here you can choose compiler, I have chosen IR
        binaries.executable()
        browser()

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("io.kinference:inference-core:0.1.13")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
            }
        }
    }
}
