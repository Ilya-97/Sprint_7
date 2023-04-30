import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class OrderCreate extends Courier {

    private final String firstNameTest;
    private final String lastNameTest;
    private final String addressTest;
    private final int metroStationTest;
    private final String phoneTest;
    private final int rentTimeTest;
    private final String deliveryDateTest;
    private final String commentTest;
    private final String colorSettingTest;

    public OrderCreate(String firstNameTest, String lastNameTest, String addressTest, int metroStationTest, String phoneTest, int rentTimeTest, String deliveryDateTest, String commentTest, String colorSettingTest) {
        this.firstNameTest = firstNameTest;
        this.lastNameTest = lastNameTest;
        this.addressTest = addressTest;
        this.metroStationTest = metroStationTest;
        this.phoneTest = phoneTest;
        this.rentTimeTest = rentTimeTest;
        this.deliveryDateTest = deliveryDateTest;
        this.commentTest = commentTest;
        this.colorSettingTest = colorSettingTest;
    }

    @Parameterized.Parameters
    public static Object[][] testParams() {
        return new Object[][]{
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", "BLACK"},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", "GREY"},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", ""},
        };
    }

    @Test
    @DisplayName("Create order")
    @Description("Orders creates 3 times with all colors by params")
    public void createOrderTest() {
        OrderJson orderJson = new OrderJson(firstNameTest, lastNameTest, addressTest, metroStationTest, phoneTest, rentTimeTest, deliveryDateTest, commentTest, colorSettingTest);
        orderJson.setColor(new ArrayList<>(), colorSettingTest);
        Response response = given().log().all().header("Content-type", "application/json").and().body(orderJson).when().post("/api/v1/orders");
        response.then().statusCode(201).body("track", notNullValue());

    }
}
