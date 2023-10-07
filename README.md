
---

# Restaurant API

This Java Spring Boot application provides an API for managing restaurants and their menus. It allows users to perform various operations, including creating new restaurants, retrieving restaurant details, adding menu items, and more.

## Table of Contents

1. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
2. [Usage](#usage)
3. [Endpoints](#endpoints)
    - [Get Restaurants](#get-restaurants)
    - [Get Restaurant](#get-restaurant)
    - [Get Menu Item](#get-menu-item)
    - [Create Restaurant](#create-restaurant)
    - [Delete Restaurant](#delete-restaurant)
    - [Add Menu Item](#add-menu-item)
4. [Data Structures](#data-structures)
    - [Restaurant](#restaurant)
    - [Item](#item)
    - [ResourceResponse](#resourceresponse)

---

## Getting Started

### Prerequisites

- Java 8 or higher
- Spring Boot

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/username/restaurant-api.git
    cd restaurant-api
    ```

2. Build and run the application:

    ```bash
    ./mvnw spring-boot:run
    ```

---

## Usage

This API provides endpoints for interacting with restaurants and their menus. Ensure you have the necessary prerequisites installed before proceeding.

---

## Endpoints

### Get Restaurants

- **Endpoint**: `GET /v1/restaurants`
- **Description**: Retrieves a list of restaurants, optionally filtered by cuisine.

### Get Restaurant

- **Endpoint**: `GET /v1/restaurants/{id}`
- **Description**: Retrieves details of a specific restaurant by its ID.

### Get Menu Item

- **Endpoint**: `GET /v1/restaurants/{id}/menu/item/{itemId}`
- **Description**: Retrieves details of a specific menu item within a restaurant.

### Create Restaurant

- **Endpoint**: `POST /v1/restaurants`
- **Description**: Creates a new restaurant.

### Delete Restaurant

- **Endpoint**: `DELETE /v1/restaurants/{id}`
- **Description**: Deletes a restaurant by its ID.

### Add Menu Item

- **Endpoint**: `POST /v1/restaurants/{id}/menu/item`
- **Description**: Adds a new menu item to a restaurant.

---

## Data Structures

### Restaurant

- `name`: The name of the restaurant.
- `cuisine`: The type of cuisine served by the restaurant.
- `menu`: A list of menu items.
- `id`: A unique identifier for the restaurant.

### Item

- `name`: The name of the menu item.
- `image`: The URL of an image representing the menu item.
- `id`: A unique identifier for the menu item.
- `available`: Indicates whether the item is currently available.

### ResourceResponse

- `resource`: The URL of the created resource.
- `message`: A message indicating the status of the request.

---