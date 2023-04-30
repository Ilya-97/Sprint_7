import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierDelete extends Courier {
    @Test
    @DisplayName("Delete courier valid Test")
    @Description("Delete courier after using or idk")
    public void createCourierValid() {
        createCourier(newUser);
        CourierId courierId = loginCourier(loginForm).as(CourierId.class);
        Response response = given().header("Content-type", "application/json").and().body(courierId).delete("/api/v1/courier/" + courierId.getId() + "");
        response.then().statusCode(200).body("ok", equalTo(true));
    }
}

