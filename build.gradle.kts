plugins {
    val kotlinVersion = "1.5.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.7.1"
}

group = "com.billyang"
version = "0.1.0"

repositories {
    mavenLocal()
    maven{ url =uri("https://maven.aliyun.com/nexus/content/groups/public/")}
    mavenCentral()
}

dependencies{
    //在IDE内运行的mcl添加滑块模块，请参考https://github.com/project-mirai/mirai-login-solver-selenium把版本更新为最新
    //runtimeOnly("net.mamoe:mirai-login-solver-selenium:1.0-dev-15")
    implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("*.jar"))))
}