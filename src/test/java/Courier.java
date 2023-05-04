import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class Courier {

    UserData newUser = new UserData("katamaran", "qwerty", "saske");
    UserData loginForm = new UserData("katamaran", "qwerty");
    UserData loginFormInvalid = new UserData("ninja", "4321", "saske");
    UserData loginFormWithoutParam = new UserData("ninja", "");
    UserData createFormWithoutParam = new UserData("ninja");


    public Response createCourier(Object object) {
        Response response = given().header("Content-type", "application/json").and().body(object).post("/api/v1/courier");
        return response;
    }

    public Response loginCourier(Object object) {
        Response responseLogin = given().header("Content-type", "application/json").body(object).post("/api/v1/courier/login");
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
