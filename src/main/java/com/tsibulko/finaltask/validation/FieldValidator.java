package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.GenericDAO;
import com.tsibulko.finaltask.dao.impl.JdbcDaoFactory;

import java.util.regex.Pattern;

public class FieldValidator {
    private static final String INTEGER_PATTERN = "[+-]?(\\d){1,9}";
    private static final String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    private static final FieldValidator INSTANCE = new FieldValidator();
    private final Pattern integerPattern = Pattern.compile(INTEGER_PATTERN);

    private FieldValidator() {
    }

    public static FieldValidator getInstance() {
        return INSTANCE;
    }

    public void simpleStingMatches(String string, int maxLength, int minLength, String fieldName) throws ValidationException {
        if (string == null || string.length() > maxLength || string.length() < minLength || string.trim().equals("")) {
            throw new ValidationException("not valid " + fieldName + " :" + string);
        }
    }

    public void isMatcesByPattern(String patternString, String testString) throws ValidationException {
        Pattern pattern = Pattern.compile(patternString);
        if (testString == null || !pattern.matcher(testString).matches()) {
            throw new ValidationException("Not valid " + testString);
        }

    }


    public void isMatchesInt(String stringInt, int[] range) throws ValidationException {
        if (stringInt == null) {
            throw new ValidationException("String number is null");
        }
        boolean isMatches;
        isMatches = integerPattern.matcher(stringInt).matches();
        if (isMatches) {
            int number = Integer.parseInt(stringInt);
            if (range == null || range.length != 2) {
                throw new IllegalArgumentException("Array length should be equals 2");
            }
            if (number < range[0] || number > range[1]) {
                isMatches = false;
            }
        }
        if (!isMatches) {
            throw new ValidationException("String: " + stringInt + "not valid");
        }
    }

    public void isExist(String fieldName, Class curentClass, String value) throws DaoException, ValidationException {
        GenericDAO dao = JdbcDaoFactory.getInstance().getDao(curentClass);
        if (!dao.findStringsFromColumn(fieldName).contains(value)) {
            throw new ValidationException(value + " in " + fieldName + "doesn`t exist!");
        }
    }


    public void isUnique(String[] fieldName, Class curentClass, String... value) throws DaoException, ValidationException {
        GenericDAO dao = JdbcDaoFactory.getInstance().getDao(curentClass);
        for (int i = 0; i <= fieldName.length - 1; i++) {
            if (dao.findStringsFromColumn(fieldName[i]).contains(value[i])) {
                throw new ValidationException(value + " in " + fieldName[i] + "doesn`t exist!");
            }
        }

    }
}

