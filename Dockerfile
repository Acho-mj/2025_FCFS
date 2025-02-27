# 베이스 이미지 (OpenJDK 21 사용)
FROM openjdk:21-jdk-slim

# JAR 파일을 컨테이너로 복사
COPY build/libs/fcfs-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너 실행 시 JAR 파일 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

# 컨테이너가 실행될 포트
EXPOSE 8080
