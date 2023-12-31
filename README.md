# vault-spring-boot
## Spring Boot Vault Demo
This is a demo project to show how to use Spring Boot with Vault. It uses the Vault Java Driver to connect to Vault and retrieve secrets. It also uses the Vault Spring Boot Starter to configure the Vault Java Driver and to inject the VaultTemplate into the application.

## Install Vault on Docker
``` 
docker run -d --name vault -p 8200:8200 -e 'VAULT_DEV_ROOT_TOKEN_ID=dev-only-token' hashicorp/vault
```
Then you can access the Vault UI at http://localhost:8200/ui

Login using the token: `dev-only-token`


## Store Secrets in Vault
Send POST request to /secret
![img.png](assets/img.png)

secret is stored in Vault
![img.png](assets/img2.png)

## Get secrets from Vault
Send GET request to /secret. We get encrypted secret from Vault
![img.png](assets/img3.png)

## Decrypt secrets from Vault
Send POST request to /secret/decrypt. We get decrypted secret from Vault
![img.png](assets/img4.png)