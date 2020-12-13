package by.epam.bikesharing.validation;

public class DateValidation {

    private static final String MONTH_REGEX = "\\d{1,2}";
    private static final String YEAR_REGEX = "\\d{2}";

    public static boolean isValidYear(String cvv) {
        return cvv.matches(YEAR_REGEX);
    }

    public static boolean isValidMonth(String month) {
        if (month.matches(MONTH_REGEX)) {
            int intMonth = Integer.parseInt(month);
            return (intMonth > 0 && intMonth < 13);
        }
        return false;
    }
}