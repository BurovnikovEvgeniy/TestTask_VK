package Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {

    public static String generateString(int length) {
        return RandomStringUtils.random(length, true, false);
    }
}
