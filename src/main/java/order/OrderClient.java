package order;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";

    // Создание нового заказа
    public static ValidatableResponse createOrder(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(order)
                .when()
                .post("/api/v1/orders")
                .then();
    }
    // Получаем список заказов
    public ValidatableResponse orderList() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .when()
                .get("/api/v1/orders")
                .then();
    }

    // Удаление тестового заказа
    public ValidatableResponse deleteOrder(int track) {
        return  given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(track)
                .when()
                .put("/api/v1/orders/cancel")
                .then();
    }
}
