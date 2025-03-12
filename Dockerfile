# 使用 Amazon Corretto 17 作为基础镜像
FROM amazoncorretto:17-alpine

# 设置环境变量
ENV HELLO="Hello from the enclave side!"

# 将你的 Spring Boot JAR 文件复制到容器中
COPY /usr/share/nitro_enclaves/tmd-nitro-kms/tmd-nitro-kms-0.0.1.jar /app/tmd-nitro-kms-0.0.1.jar

# 设置工作目录
WORKDIR /app

# 执行 Spring Boot 应用
CMD ["java", "-jar", "tmd-nitro-kms-0.0.1.jar"]
