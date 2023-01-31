import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import courier.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTest {
    protected final CourierGenerator courierGenerator = new CourierGenerator();
    private int id;
    private Courier courier;
    private CourierClient courierClient;

    // Ассерты успешного создания курьера
    public void creationCourierCompleted(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_CREATED)
                .body("ok", is(true));
    }

    // Ассерты ошибки создания курьера из-за недозаполненных параметров
    public void creationCourierFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    // Ассерты ошибки создания курьера из-за дублирования логина
    public void recurringLogin(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }


    @Test
    @DisplayName("Создание курьера с заполнением всех полей")
    public void courierCanBeCreated() {
        courier = courierGenerator.getRandom();
        ValidatableResponse response = courierClient.createCourier(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        id = loginResponse.extract().path("id");
        creationCourierCompleted(response);
    }

    @Test
    @DisplayName("Создание курьера без параметра firstName")
    public void courierWithoutFirstNameCanBeCreated() {
        courier = courierGenerator.getRandomWithoutFirstName();
        ValidatableResponse response = courierClient.createCourier(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        id = loginResponse.extract().path("id");
        creationCourierCompleted(response);
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void courierWithoutPasswordCanNotBeCreated() {
        courier = courierGenerator.getRandomWithoutPassword();
        ValidatableResponse response = courierClient.createCourier(courier);
        creationCourierFailed(response);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void courierWithoutLoginCanNotBeCreated() {
        courier = courierGenerator.getRandomWithoutLogin();
        ValidatableResponse response = courierClient.createCourier(courier);
        creationCourierFailed(response);
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров")
    public void cannotBeCreatedTwoSameCouriers() {
        courier = courierGenerator.getRandom();
        courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.createCourier(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        id = loginResponse.extract().path("id");
        recurringLogin(response);
    }


    @After
    public void cleanUp() {
        if (id != 0) {
            courierClient.deleteCourier(id);
        }
    }


}
