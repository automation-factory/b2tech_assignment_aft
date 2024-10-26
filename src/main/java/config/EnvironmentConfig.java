package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({ConfigSources.SYSTEM_PROPS, ConfigSources.SYSTEM_ENV, ConfigSources.CONFIGURATION_FILE})
public interface EnvironmentConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("timeout")
    int timeout();

    @Key("is.headless")
    boolean isHeadless();

}
