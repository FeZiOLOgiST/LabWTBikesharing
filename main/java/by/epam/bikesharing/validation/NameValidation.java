package by.epam.bikesharing.validation;

public class NameValidation {

    private static final String NAME_REGEX = "[a-zA-Z]{3,30}";

    public static boolean isValidName(String name) {
        return name.matches(NAME_REGEX);
    }
}
