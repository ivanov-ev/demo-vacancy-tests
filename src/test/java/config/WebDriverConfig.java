package config;

import org.aeonbits.owner.Config;

@Config.Sources(
        {"classpath:${env}.properties"}
)
public interface WebDriverConfig extends Config {

    @DefaultValue("chrome")
    String browser();

    String browserVersion();

    @DefaultValue("1920x1000")
    String browserSize();

    String remote();

    String remoteUsername();

    String remotePassword();
}
