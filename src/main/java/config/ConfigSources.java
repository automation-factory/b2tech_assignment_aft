package config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConfigSources {

    public static final String CONFIGURATION_FILE = "classpath:config.${test.environment}.properties";
    public static final String SYSTEM_ENV = "system:env";
    public static final String SYSTEM_PROPS = "system:properties";
}
