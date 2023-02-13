package courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class AuthMetods {
    @Step("Ассерты успешной авторизации")
    public int authIsDone(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(SC_OK)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }
    @Step("Ассерты ошибки авторизации")
    public void authFail(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Step("Ассерты ошибки авторизации под несуществующим пользователем")
    public void authWithNonExistentUserFail(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
