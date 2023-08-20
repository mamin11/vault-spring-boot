package amin.vaultspringboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
@PropertySource(value = {"vault.properties"})
public class VaultConfig extends AbstractVaultConfiguration {

    @Value("${spring.cloud.vault.token}")
    private String token;

    @Value("${spring.cloud.vault.scheme}")
    private String scheme;

    @Value("${spring.cloud.vault.host}")
    private String host;

    @Value("${spring.cloud.vault.port}")
    private int port;

    @Override
    public VaultEndpoint vaultEndpoint() {
        VaultEndpoint vaultEndpoint = new VaultEndpoint();
        vaultEndpoint.setHost(host);
        vaultEndpoint.setPort(port);
        vaultEndpoint.setScheme(scheme);

        return vaultEndpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication(token);
    }
}
