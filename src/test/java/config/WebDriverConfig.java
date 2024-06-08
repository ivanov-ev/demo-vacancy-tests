package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})
public interface WebDriverConfig extends Config {

    @Key("selenide.browser")
    @DefaultValue("chrome")
    String browser();

    @Key("selenide.browserVersion")
    String browserVersion();

    @Key("selenide.browserSize")
    @DefaultValue("1920x1000")
    String browserSize();

    @Key("selenide.remote")
    String remote();

    @Key("selenide.user")
    @DefaultValue("user1")
    String remoteUsername();

    @Key("selenide.password")
    @DefaultValue("1234")
    String remotePassword();
}
