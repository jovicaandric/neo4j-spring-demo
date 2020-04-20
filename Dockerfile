FROM maven
WORKDIR /app
COPY . .
RUN mvn package
CMD java -jar target/*.jar