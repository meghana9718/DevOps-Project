# Stage 1: Build
FROM openjdk:17-slim AS build
WORKDIR /app
COPY . .
RUN javac *.java

# Stage 2: Run
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app .
CMD ["java", "Main"]