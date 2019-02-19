package com.tsibulko.finaltask.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean checkWithRegExp(String checkedString, String regEx) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(checkedString);
        return m.matches();
    }
}
