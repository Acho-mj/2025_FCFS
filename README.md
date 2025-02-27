## **🚀목표: Jenkins + Docker를 이용한 CI/CD 구축**

1. Jenkins가 GitHub Webhook을 통해 변경 사항을 감지
2. Spring Boot 프로젝트를 빌드 (`./gradlew build`)
3. jenkins가 Docker 이미지를 생성하고 Docker Hub로 푸시
4. EC2에서 컨테이너를 자동으로 실행 또는 재배포


![Image](https://github.com/user-attachments/assets/c672de11-014a-479c-8518-b5e836a44d0c)


## jenkins Execute shell (freestyle)
```
# Gradle 빌드 실행
chmod +x ./gradlew
./gradlew clean build

# Docker login
echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin

# Docker 이미지 빌드
docker build -t achomj/docker-fcfs:0.$BUILD_NUMBER ./

# Docker Hub에 이미지 푸시
docker push achomj/docker-fcfs:0.$BUILD_NUMBER

# EC2 서버에서 실행 중인 기존 Docker 컨테이너 중지 및 삭제
ssh -o StrictHostKeyChecking=no -i $EC2_SSH_KEY ec2-user@$EC2_IP "docker stop fcfs-container || true && docker rm fcfs-container || true"

# Docker 이미지 Pull
ssh -o StrictHostKeyChecking=no -i $EC2_SSH_KEY ec2-user@$EC2_IP "docker pull achomj/docker-fcfs:0.$BUILD_NUMBER"

# EC2 서버에서 새 컨테이너 실행
ssh -o StrictHostKeyChecking=no -i $EC2_SSH_KEY ec2-user@$EC2_IP \
  "docker run -d --name fcfs-container -p 8080:8080 \
  -e DB_URL='${DB_URL}' \
  -e DB_USERNAME='${DB_USERNAME}' \
  -e DB_PASSWORD='${DB_PASSWORD}' \
  achomj/docker-fcfs:0.$BUILD_NUMBER"
```
