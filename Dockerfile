# ---------- STAGE 1: BUILD (compile .java) ----------
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /src

# Copy only Java sources (faster caching on rebuilds)
COPY Main.java Student.java StudentService.java ./

# Compile classes to /out
RUN javac -g:none -d /out Main.java Student.java StudentService.java


# ---------- STAGE 2: RUNTIME (smaller JRE) ----------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy compiled classes
COPY --from=build /out /app/classes

# (Optional) If your app serves static files, copy them
# Remove these lines if not needed
COPY index.html style.css script.js /app/public/

# Use a custom port (change if you prefer)
ENV APP_PORT=9090
EXPOSE 9090

# Run your app (ensure Main binds to 0.0.0.0 and uses APP_PORT)
WORKDIR /app/classes
CMD ["java", "Main"]