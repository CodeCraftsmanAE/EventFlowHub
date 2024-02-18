# DatasourceToKafkaService

This microservice fetches data from the News API at regular intervals using the `@Scheduled` annotation in a Spring Boot application. 
The fetched data will then be sent to Kafka for further processing.

This project utilizes the [News-API-Java](https://github.com/KwabenBerko/News-API-Java) library, a Java client for the News API. This library simplifies the process of interacting with the News API and fetching news data.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Usage](#usage)

## Features

- Fetches data from the News API at regular intervals.
- Sends the fetched data to Kafka for further processing.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17 or later
- Maven
- Kafka setup 

## Getting Started

To get started with this microservice, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/CodeCraftsmanAE/EventFlowHub.git

2. Build the project:

   ```bash
   cd EventFlowHub
   mvn clean install

3. Run the project:
    ```bash
   mvn spring-boot:run

## Usage

The microservice will automatically fetch data from the News API at regular intervals using the @Scheduled annotation. Adjust the news.api.scheduleRate based on your requirements.


## Configuration

Configure the microservice by updating the application.yml file. 
Specify the API key (in my case it is stored in an env variable), 
   ```bash   
   news:
      api:
         url: http://eventregistry.org/api/v1/minuteStreamArticles
         apiKey: ${NEWSAPI_API_KEY}
         keywords: keywords separated by AND/OR => gaza AND israel
         lang: en
         pageSize: 100
         scheduleRate: 1200000

