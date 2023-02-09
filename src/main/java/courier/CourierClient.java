package courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    private static final String PATH_COURIER = "/api/v1/courier";
    private static final String PATH_LOGIN = "/api/v1/courier/login";

    @Step("Создаем курьера")
    public ValidatableResponse createCourier(Courier courier){
        return given().log().all()
                .spec(Client.getSpec())
                .body(courier)
                .when()
                .post(PATH_COURIER)
                .then();
    }
    @Step("Логин курьера")
    public ValidatableResponse login (CourierCredentials credentials) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(credentials)
                .when()
                .post(PATH_LOGIN)
                .then();
    }
    @Step("Удаляем созданного курьера")
    public void deleteCourier(int id) {
        given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .baseUri(BASE_URL)
                .delete(PATH_COURIER + id)
                .then();
    }
}
