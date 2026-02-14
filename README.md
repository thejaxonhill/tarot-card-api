# Tarot Card API

A Java Spring Boot web server providing Tarot card information and images. This project offers a simple API to retrieve card details, draw random cards, and access high-quality Tarot card imagery.

### Features

- **Card Directory**: Access a full list of Tarot cards with detailed information including meanings (upright and reversed), descriptions, and suits.
- **Random Draws**: Draw one or multiple cards for readings, with the ability to exclude already drawn cards.
- **Image Hosting**: Serves card images directly through the API.
- **OpenAPI Integration**: Includes Swagger UI for easy API exploration and testing.

### Technologies

- **Java 21** & **Kotlin 2.3.10**
- **Spring Boot 3.5.4**
- **Spring Web**
- **Spring Boot Actuator**
- **Springdoc OpenAPI (Swagger UI)**
- **Maven**

### Getting Started

#### Prerequisites

- JDK 21 or higher
- Maven 3.9+

#### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/thejaxonhill/tarot-card-api.git
   cd tarot-card-api
   ```

2. Build and run using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

The server will start on `http://localhost:8080` by default.

### API Endpoints

The base URL for the API is `/api/v1/cards`.

| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/api/v1/cards` | `GET` | Get a paginated list of all Tarot cards. |
| `/api/v1/cards/{id}` | `GET` | Get details for a specific card by ID. |
| `/api/v1/cards/draw-card` | `GET` | Draw a single random card. |
| `/api/v1/cards/draw-cards` | `GET` | Draw a specified number of random cards. |
| `/api/v1/cards/{filename}.jpg` | `GET` | Retrieve the image for a specific card. |

#### Query Parameters

- **Pagination** (for `/api/v1/cards`):
    - `page` (default: 0)
    - `size` (default: 10)
- **Drawing Cards**:
    - `amount` (default: 3, for `/draw-cards`)
    - `alreadyDrawn` (list of IDs to exclude)

### Documentation

Once the application is running, you can access the interactive API documentation (Swagger UI) at:
`http://localhost:8080/swagger-ui.html`

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
