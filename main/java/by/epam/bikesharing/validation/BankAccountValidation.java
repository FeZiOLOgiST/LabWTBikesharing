package by.epam.bikesharing.validation;

public class BankAccountValidation {

    private static final String CVV_REGEX = "^[0-9]{3,4}$";
    private static final String NUMBER_REGEX = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";

    public static boolean isValidCvv(String cvv) {
        return cvv.matches(CVV_REGEX);
    }

    public static boolean isValidNumber(String number) {
        return number.matches(NUMBER_REGEX);
    }
}