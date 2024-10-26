package config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.aeonbits.owner.ConfigFactory;

import java.util.List;
import java.util.Locale;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static org.apache.commons.lang3.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Config {
    private static final String LOCAL_ENV = "local";
    private static final String CI_ENV = "ci";
    private static final String TEST_ENVIRONMENT_PROPERTY = "test.environment";

    static {
        environmentConfig();
    }

    public static EnvironmentConfig environmentConfig() {
        ConfigFactory.setProperty(TEST_ENVIRONMENT_PROPERTY, readEnvironment());
        return EnvironmentConfigHolder.ENVIRONMENT_CONFIG;
    }

    private static <T extends org.aeonbits.owner.Config> T create(Class<? extends T> clazz) {
        return ConfigFactory.create(clazz, System.getProperties(), getenv());
    }

    private static @NonNull String readEnvironment() {
        var env = isBlank(getenv(TEST_ENVIRONMENT_PROPERTY)) ?
                getProperty(TEST_ENVIRONMENT_PROPERTY) : getenv(TEST_ENVIRONMENT_PROPERTY);
        env = env.trim();

        if (!List.of(LOCAL_ENV, CI_ENV).contains(env.toLowerCase(Locale.ROOT))) {
            throw new IllegalArgumentException(format("Environment [%s] is not supported!", env));
        }
        return env;
    }

    private static final class EnvironmentConfigHolder {
        private static final EnvironmentConfig ENVIRONMENT_CONFIG = create(EnvironmentConfig.class);
    }

}
