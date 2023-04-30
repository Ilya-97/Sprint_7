import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest extends Courier {
    @Test
    @DisplayName("Create courier valid Test")
    @Description("Create courier. Very important case")
    public void createCourierValid() {
        createCourier(newUser).then().statusCode(201).body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Create courier invalid Test")
    @Description("If login already used.")
    public void createCourierAlreadyExists() {
        createCourier(newUser);
        createCourier(newUser).then().statusCode(409).body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Create courier invalid Test")
    @Description("Not enough data to create an account")
    public void createCourierInvalidParams() {
        createCourier(createFormWithoutParam).then().statusCode(400).body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
