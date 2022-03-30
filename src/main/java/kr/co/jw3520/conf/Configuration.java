package kr.co.jw3520.conf;

import java.util.ResourceBundle;

public class Configuration extends AbstractConfiguration{
    public Configuration() throws Exception {
        init();
    }

    private void init() throws Exception {
        try {
            if(RESOURCE_BUNDLE == null) {
                RESOURCE_BUNDLE = ResourceBundle.getBundle("vac-server");
            }
        } catch(Exception e) {
            throw new Exception("Can't load configuration file : vac-server");
        }
    }
}
