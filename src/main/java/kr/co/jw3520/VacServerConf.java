package kr.co.jw3520;

import kr.co.jw3520.conf.Config;
import kr.co.jw3520.conf.Configuration;

import java.util.HashMap;
import java.util.Map;

public class VacServerConf {
    private static VacServerConf instance = new VacServerConf();
    private String[] arrVanType;
    private Map<String, VanConnInfo> mapVanConf = new HashMap<>();
    private VacServerConf() {
        init();
    }
    public static VacServerConf getInstance() {
        return instance;
    }

    public String[] getArrVanType() {
        return arrVanType;
    }

    public int getPort(String vanType) {
        return mapVanConf.get(vanType).getPort();
    }

    private void init() {
        try {
            Config conf = new Configuration();
            String vanList = conf.getString("van.list", "a");
            arrVanType = vanList.split(",");

            for (String van : arrVanType) {
                String vanType = van;
                VanConnInfo vanConnInfo = new VanConnInfo();
                vanConnInfo.setPort(conf.getInt(vanType + ".server.port", 15000));
                mapVanConf.put(vanType, vanConnInfo);
            }
        } catch (Exception e) {
            //logging
        }
    }
    class VanConnInfo {
        int port = 0;

        public int getPort() {
            return port;
        }
        public VanConnInfo setPort(int port) {
            this.port = port;
            return this;
        }
    }
}
