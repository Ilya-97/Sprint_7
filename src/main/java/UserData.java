import org.apache.commons.lang3.RandomStringUtils;

public class UserData {
    public String randomLogin;
    public String randomPassword;
    public String randomFirstName;

    public UserData() {
        this.randomLogin = RandomStringUtils.randomAlphabetic(8);
        this.randomPassword = RandomStringUtils.randomAlphabetic(8);
        this.randomFirstName = RandomStringUtils.randomAlphabetic(8);
    }
}
