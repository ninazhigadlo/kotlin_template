plugins {
    val kotlinVersion = "1.4.21"

    kotlin("jvm") version kotlinVersion
    application
//    id("java")
    idea
//    `java-library`
    kotlin("plugin.serialization") version kotlinVersion
    kotlin("kapt") version kotlinVersion // used by dsl-json
}

repositories {
    mavenCentral()
    jcenter()
    maven("https://dl.bintray.com/cppexpert/maven")
    maven("https://repo.spring.io/snapshot")
    maven("https://repo.spring.io/release")
    maven("https://dl.bintray.com/cfraser/muirwik")
    maven("https://s3.amazonaws.com/redshift-maven-repository/release")
    maven("https://repository.mulesoft.org/nexus/content/repositories/public/")

    flatDir {
        dirs("libs")
    }
}

dependencies {
    val kotlinVersion = "1.4.21"
    val ktorVersion = "1.5.1"

    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))

    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.4.1")

    implementation("org.jdbi:jdbi3-kotlin:3.18.0")
    implementation("org.jdbi:jdbi3-postgres:3.18.0")
    implementation("com.walkmind.extensions:serializers:1.4")
    implementation("com.walkmind.extensions:collections:1.19")
    implementation("org.postgresql:postgresql:42.2.18")
    implementation("com.h2database:h2:1.4.200")
    implementation("com.zaxxer:HikariCP:3.4.5")

    implementation(fileTree("libs"))

    implementation("org.asynchttpclient:async-http-client:2.12.1") // For ByteBufUtils only

    implementation("org.rocksdb:rocksdbjni:6.13.3")
    implementation("io.netty:netty-buffer:4.1.58.Final")
    implementation("io.netty:netty-transport-native-epoll:4.1.58.Final") // For ktor
    implementation("io.projectreactor:reactor-core:3.4.0")
    implementation("io.projectreactor.netty:reactor-netty:1.0.1")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.0")

    implementation("org.apache.logging.log4j:log4j-core:2.14.0")
    implementation("org.apache.logging.log4j:log4j-api:2.14.0")

    implementation("org.apache.commons:commons-csv:1.8")
    implementation("commons-io:commons-io:2.8.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("org.apache.commons:commons-pool2:2.9.0")
    implementation("commons-codec:commons-codec:1.15")
    implementation("com.google.guava:guava:29.0-jre")
    implementation("com.typesafe:config:1.4.0")
    implementation("info.picocli:picocli:4.5.2")

    implementation("com.github.luben:zstd-jni:1.4.5-6")
    implementation("org.apache.avro:avro:1.10.0")

    implementation("io.micrometer:micrometer-core:1.5.5")
    implementation("io.micrometer:micrometer-registry-prometheus:1.5.5")
    implementation("io.micrometer:micrometer-registry-cloudwatch2:1.5.5")

    implementation("com.squareup.moshi:moshi:1.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.11.0")
    implementation("com.squareup.moshi:moshi-adapters:1.11.0")

    implementation("com.fasterxml.jackson.core:jackson-core:2.11.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-smile:2.11.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-avro:2.11.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.11.2")
    implementation("org.msgpack:jackson-dataformat-msgpack:0.8.20")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.2")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.2")


    testImplementation(kotlin("test-junit5", kotlinVersion))
    testImplementation("io.projectreactor:reactor-test:3.4.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.rocksdb:rocksdbjni:6.13.3")
    testImplementation("io.netty:netty-buffer:4.1.58.Final")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    // Define the main class for the application.
    mainClass.set("com.walkmind.AppKt")
}

tasks {
    withType(Test::class.java) {
        testLogging.showStandardStreams = true
        testLogging.showExceptions = true
        useJUnitPlatform {}
    }

    withType(Wrapper::class.java).configureEach {
        gradleVersion = "6.7.1"
        distributionType = Wrapper.DistributionType.BIN
    }
}