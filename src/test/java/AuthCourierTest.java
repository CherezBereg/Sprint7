import courier.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import org.junit.After;
import org.junit.Test;

public class AuthCourierTest {
    private Courier courier;
    private CourierClient courierClient = new CourierClient();
    private AuthMetods authMetods = new AuthMetods();
    private int id;

    @Test
    @DisplayName("Успешная авторизация со всеми полями")
    public void authPossible() {
        courier = CourierGenerator.getRandom();
        courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        id = response.extract().path("id");
        authMetods.authIsDone(response);
    }
    @Test
    @DisplayName("Попытка авторизации без пароля")
    public void authWithoutPasswordImpossible() {
        courier = CourierGenerator.getRandomWithoutPassword();
        courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        authMetods.authFail(response);
    }
    @Test
    @DisplayName("Попытка авторизации без логина")
    public void authWithoutLoginImpossible() {
        courier = CourierGenerator.getRandomWithoutLogin();
        courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        authMetods.authFail(response);
    }
    @Test
    @DisplayName("Попытка входа под несуществующим пользователем")
    public void authWithNonExistentUserImpossible() {
        courier.setLogin("iAmNotHere");
        courier.setPassword("488393");
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        authMetods.authWithNonExistentUserFail(response);
    }
    @After
    public void cleanUp() {
        if (id != 0) {
            courierClient.deleteCourier(id);
        }
    }
}
