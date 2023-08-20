# vault-spring-boot
## Spring Boot Vault Demo
This is a demo project to show how to use Spring Boot with Vault. It uses the Vault Java Driver to connect to Vault and retrieve secrets. It also uses the Vault Spring Boot Starter to configure the Vault Java Driver and to inject the VaultTemplate into the application.

## Install Vault on Docker
``` 
docker run -d --name vault -p 8200:8200 -e 'VAULT_DEV_ROOT_TOKEN_ID=dev-only-token' hashicorp/vault
```
Then you can access the Vault UI at http://localhost:8200/ui

Login using the token: `dev-only-token`

