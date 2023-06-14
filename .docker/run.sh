cd ..
./mvnw clean package
cd .docker
docker-compose up -d --build