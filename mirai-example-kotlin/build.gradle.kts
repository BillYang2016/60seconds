plugins {
    val kotlinVersion = "1.4.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.0.0"
}

mirai{
    coreVersion = "2.1.0"
}
group = "org.example"
version = "0.1.0"

repositories {
    jcenter()
    //���ھ���Դ
    //��Ϊ��
    maven { url =uri("https://mirrors.huaweicloud.com/repository/maven") }
    //������
    maven { url =uri("https://maven.aliyun.com/nexus/content/repositories/jcenter")}
    //mirai-console�Ĳֿ�
    maven {url = uri("https://dl.bintray.com/him188moe/mirai")}
    //����ģ��Ĳֿ�
    // maven { url = uri("https://dl.bintray.com/karlatemp/misc") }
    mavenLocal()
    mavenCentral()
}
dependencies{
    //��IDE�����е�mcl��ӻ���ģ�飬��ο�https://github.com/project-mirai/mirai-login-solver-selenium�Ѱ汾����Ϊ����
    //runtimeOnly("net.mamoe:mirai-login-solver-selenium:1.0-dev-15")
}