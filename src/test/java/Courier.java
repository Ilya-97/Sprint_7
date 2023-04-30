import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class Courier {

    UserData userData = new UserData();
    String newUser = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"" + userData.randomPassword + "\", \"firstName\": \"" + userData.randomFirstName + "\"}";
    String loginForm = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"" + userData.randomPassword + "\"}";
    String loginFormInvalid = "{\"login\": \"" + userData.randomPassword + "\", \"password\": \"" + userData.randomLogin + "\"}";
    String loginFormWithoutParam = "{\"login\": \"" + userData.randomLogin + "\", \"password\": \"\"}";
    String createFormWithoutParam = "{\"login\": \"" + userData.randomLogin + "\"}";


    public Response createCourier(String paramNewUser) {
        Response response = given().header("Content-type", "application/json").and().body(paramNewUser).post("/api/v1/courier");
        return response;
    }

    public Response loginCourier(String formLogin) {
        Response responseLogin = given().header("Content-type", "application/json").body(formLogin).post("/api/v1/courier/login");
        return responseLogin;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void courierDelete() {
        CourierId courierId = given().header("Content-type", "application/json").body(loginForm).post("/api/v1/courier/login").body().as(CourierId.class);
        given().header("Content-type", "application/json").and().body(courierId).delete("/api/v1/courier/" + courierId.getId() + "");
    }


}
