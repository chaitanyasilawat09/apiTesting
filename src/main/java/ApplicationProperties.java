import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {
    INSTANCE;

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public int getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }

    public String getUserName() {
        return properties.getProperty("username"); }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getMasterAccount() {
        return properties.getProperty("masterAccount");
    }

    public String getAccountId() {
        return properties.getProperty("accountId");
    }

    public String getKeyId() {
        return properties.getProperty("keyId");
    }

    public String getAccessKey() {
        return properties.getProperty("accessKey");
    }
    public String getSubscriptions() {
        return properties.getProperty("subscriptions");
    }

    public String getTenantId() {
        return properties.getProperty("tenantId");
    }

    public String getClientId() {
        return properties.getProperty("clientId");
    }

    public String getSecret() {
        return properties.getProperty("secret");
    }

    public String getServerName() {
        return properties.getProperty("serverName");
    }

    public String getAzureServerName() {
        return properties.getProperty("azureServerName");
    }

    public String getAwsServerName() {
        return properties.getProperty("awsServerName");
    }

    public String getAwsUserName() {
        return properties.getProperty("awsUserName");
    }

    public String getAwsPassword() {
        return properties.getProperty("awsPassword");
    }

    public String getAzureUserName() {
        return properties.getProperty("azureUserName");
    }

    public String getAzurePassword() {
        return properties.getProperty("azurePassword");
    }

    public String getAwsImage() { return properties.getProperty("awsImage");
    }

    public String getAzureImage() { return properties.getProperty("azureImage");
    }
}
