# 베이스 이미지 (OpenJDK 21 사용)
FROM openjdk:21-jdk-slim

ARG DB_HOST
ARG DB_PORT
ARG DB_USERNAME
ARG DB_PASSWORD

# 환경 변수 설정
ENV DB_HOST=${DB_HOST}
ENV DB_PORT=${DB_PORT}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일을 컨테이너로 복사
COPY build/libs/*.jar app.jar

# 컨테이너 실행 시 JAR 파일 실행
CMD ["java", "-jar", "app.jar"]

# 컨테이너가 실행될 포트
EXPOSE 8080
