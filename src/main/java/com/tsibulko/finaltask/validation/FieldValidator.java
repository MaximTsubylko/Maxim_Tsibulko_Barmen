package com.tsibulko.finaltask.validation;

import com.tsibulko.finaltask.dao.DaoException;
import com.tsibulko.finaltask.dao.DaoFactoryType;
import com.tsibulko.finaltask.dao.FactoryProducer;
import com.tsibulko.finaltask.dao.GenericDAO;

import java.util.regex.Pattern;

public class FieldValidator {
    private static final String INTEGER_PATTERN = "[+-]?(\\d){1,9}";
    private static final String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    private static final FieldValidator INSTANCE = new FieldValidator();
    private final Pattern integerPattern = Pattern.compile(INTEGER_PATTERN);
    private final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    private FieldValidator() {
    }

    public static FieldValidator getInstance() {
        return INSTANCE;
    }

    public void simpleStingMatches(String string, int maxLength, String fieldName) throws ValidationException {
        if (string == null || string.length() > maxLength || string.trim().equals("")) {
            throw new ValidationException("not valid " + fieldName + " :" + string);
        }
    }

    public void emailMatches(String email) throws ValidationException {
        if (email == null || !emailPattern.matcher(email).matches()) {
            throw new ValidationException("Not valid email!");
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
        GenericDAO dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(curentClass);
        if (!dao.findStringsFromColumn(fieldName).contains(value)) {
            throw new ValidationException(value + " in " + fieldName + "doesn`t exist!");
        }
    }

    public void isNotExist(String fieldName, Class curentClass, String value) throws DaoException, ValidationException {
        GenericDAO dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(curentClass);
        if (dao.findStringsFromColumn(fieldName).contains(value)) {
            throw new ValidationException(value + " in " + fieldName + "doesn`t exist!");
        }
    }

    public void isUnique(String[] fieldName, Class curentClass, String... value) throws DaoException, ValidationException {
        GenericDAO dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(curentClass);
        for (int i = 0; i < fieldName.length - 1; i++) {
            if (dao.findStringsFromColumn(fieldName[i]).contains(value[i])) {
                throw new ValidationException(value + " in " + fieldName[i] + "doesn`t exist!");
            }
        }

    }
}

