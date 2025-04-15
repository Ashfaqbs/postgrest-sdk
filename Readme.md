## 🔄 pgRest SDK

**pgRest SDK** is a lightweight Java SDK designed for seamless interaction with [PostgREST](https://postgrest.org/) APIs. The SDK provides simple abstractions to interact with PostgreSQL databases exposed via PostgREST, making it easier to build RESTful clients.

### 📦 Project Structure

- **`pgrest-sdk`**: The core SDK for interacting with PostgREST.
- **`example-client`**: A Spring Boot application demonstrating how to use the `pgrest-sdk` by consuming PostgREST APIs.
- **`docker-compose.yml`**: A Docker setup to run PostgREST locally for testing.

### 🚀 Features

- Simplified Java HTTP client for PostgREST.
- Spring Boot integration example (in `example-client`).
- Maven-based setup with multi-module project structure.
- Postman collection for easy API testing.
- Docker Compose setup to run PostgREST locally.

### 🛠️ Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Ashfaqbs/postgrest-sdk.git
   cd postgrest-sdk
   ```

2. **Build the SDK**:
   we can build the SDK with Maven:
   ```bash
   mvn clean install
   ```

3. **Run Example Client**:
   The example client demonstrates how to use the `pgrest-sdk`. we can add the SDK as a dependency in the `example-client/pom.xml`.

   To run the client:
   ```bash
   cd example-client
   mvn spring-boot:run
   ```

4. **Testing**:
   we can use the provided [Postman collection](example-client/emp-client.postman_collection.json) to test the API.

   Import the collection into Postman and start testing with the local PostgREST setup.

### 🐳 Docker Setup (Optional)

If we want to run PostgREST locally with Docker, use the `docker-compose.yml` file.

1. **Run Docker Compose**:
   ```bash
   docker-compose up
   ```

   This will set up PostgREST using Docker and make it available on the local port.

---

### 💡 Usage

Once the SDK is built and the example client is set up, we can interact with our PostgreSQL database through PostgREST endpoints by using the Java SDK in our project.

### 📄 License

Feel free to contribute and use the code as we wish. This project is licensed under the MIT License.

---