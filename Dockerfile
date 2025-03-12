FROM amazonlinux:2
RUN yum install -y java-11-amazon-corretto && yum clean all
COPY /home/ec2-user/nitro-enclaves/target/tmd-nitro-kms.jar /home/ec2-user/nitro-enclaves/tmd-nitro-kms.jar
WORKDIR /app
CMD ["java", "-jar", "tmd-nitro-kms.jar"]
