package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    static String randomLogin = RandomStringUtils.randomAlphabetic(2,10);
    static String randomPassword = RandomStringUtils.randomAlphabetic(4,12);
    static String randomFirstName = RandomStringUtils.randomAlphabetic(2,8);
    public static Courier getRandom() {
        return new Courier(randomLogin, randomPassword, randomFirstName);
    }
    public static Courier getRandomWithoutFirstName() {
        return new Courier(randomLogin, randomPassword, null);
    }
    public static Courier getRandomWithoutLogin() {
        return new Courier(null, randomPassword, randomFirstName);
    }
    public static Courier getRandomWithoutPassword() {
        return new Courier(randomLogin,null, randomFirstName);
    }

}
