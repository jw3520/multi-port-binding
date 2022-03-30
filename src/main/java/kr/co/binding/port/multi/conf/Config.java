package kr.co.binding.port.multi.conf;

import java.util.ResourceBundle;

public interface Config {
    public ResourceBundle getResourceBundle();
    public String getString(String key, String defVal);
    public int getInt(String key, int defVal);
    public double getDouble(String key, double defVal);
    public boolean getBoolean(String key, boolean defVal);
}