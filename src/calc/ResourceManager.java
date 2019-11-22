package calc;

import java.util.Locale;
import java.util.ResourceBundle;

enum ResourceManager {
    INSTANCE;

    private ResourceBundle resourceBundle;
    private final String name = "calc.res.message";

    ResourceManager() {
        this.resourceBundle = ResourceBundle.getBundle(name, Locale.getDefault());
    }

    public void changeLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle(name, locale);
    }

    public String getString (String key) {
        return resourceBundle.getString(key);
    }
}
