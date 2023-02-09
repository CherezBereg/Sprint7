import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.OrderClient;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest {
    @Test
    @DisplayName("Возврат списка заказов")
    public void getOrderList() {
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.orderList();
        response.assertThat()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}
