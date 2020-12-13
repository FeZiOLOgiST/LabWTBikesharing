package by.epam.bikesharing.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.bikesharing.constant.LocaleConstant.RU;
import static by.epam.bikesharing.constant.LocaleConstant.US;

public class StringManager {

    private static final String FILE = "strings";
    private static final ResourceBundle resourceBundleDefault = ResourceBundle.getBundle(FILE);
    private static final ResourceBundle resourceBundleRu = ResourceBundle.getBundle(FILE, new Locale(RU, RU.toUpperCase()));
    private static final ResourceBundle resourceBundleUs = ResourceBundle.getBundle(FILE, new Locale(US, US.toUpperCase()));
    private static StringManager INSTANCE = new StringManager();

    private StringManager() {}

    public static StringManager getInstance() {
        return INSTANCE;
    }

    public String get(String key, String locale) {
        switch (locale) {
            case RU: return resourceBundleRu.getString(key);
            case US: return resourceBundleUs.getString(key);
            default: return resourceBundleDefault.getString(key);
        }
    }
}