import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLoginTest extends Courier {
    @Test
    @DisplayName("Login courier with valid login data")
    @Description("Successful execution of the request")
    public void loginTestValid() {
        createCourier(newUser);
        loginCourier(loginForm).then().statusCode(200).body("id", notNullValue());
    }

    @Test
    @DisplayName("Login courier with invalid login data")
    @Description("Login to panel or something without data. Very important after valid create")
    public void loginTestInvalidData() {
        createCourier(newUser);
        loginCourier(loginFormInvalid).then().statusCode(404).body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Login courier without data")
    @Description("Check that we do not have user without data")
    public void loginTestEmptyData() {
        createCourier(newUser);
        loginCourier(loginFormWithoutParam).then().statusCode(400).body("message", equalTo("Недостаточно данных для входа"));
    }


}
