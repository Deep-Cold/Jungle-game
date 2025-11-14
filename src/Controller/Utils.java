package Controller;

import java.util.Random;

public class Utils {
    private static final String validNameChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random generator = new Random();

    public static String getRandomString(int len) {
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < len; i++) {
            ret.append(validNameChar.charAt(generator.nextInt(validNameChar.length())));
        }
        return ret.toString();
    }
}
