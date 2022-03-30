package kr.co.binding.port.multi.conf;

import java.util.ResourceBundle;

public abstract class AbstractConfiguration implements Config {

    protected ResourceBundle RESOURCE_BUNDLE = null;

    @Override
    public ResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }

    @Override
    public String getString(String key, String defVal) {
        String value = null;
        value = RESOURCE_BUNDLE.getString(key);
        return value == null ? defVal : value;
    }

    @Override
    public int getInt(String key, int defVal) {
        int value = 0;
        try {
            value = Integer.parseInt(RESOURCE_BUNDLE.getString(key));
        } catch (Exception e) {
            value = defVal;
        }
        return value;
    }

    @Override
    public double getDouble(String key, double defVal) {
        double value = 0;
        try {
            value = Double.parseDouble(RESOURCE_BUNDLE.getString(key));
        } catch (Exception e) {
            value = defVal;
        }
        return value;
    }

    @Override
    public boolean getBoolean(String key, boolean defVal) {
        boolean value = false;
        try {
            value = Boolean.valueOf(RESOURCE_BUNDLE.getString(key));
        } catch (Exception e) {
            value = defVal;
        }
        return value;
    }
}
