FROM maven:3.8-openjdk-17

# Set the working directory in the container
WORKDIR /app

# Copy the dependencies file to the working directory in the container
COPY . .

# Install dependencies and build the project
RUN mvn clean install

# Run the application in the container
CMD mvn spring-boot:run