package amin.vaultspringboot.controller;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Versioned;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class TestController {
    public static final String SECRET_STORAGE_NAME = "secret";
    @Autowired
    private VaultOperations vaultOperations;

    @RequestMapping("/test")
    public String test() {
        return "Hello World!";
    }

    @PostMapping("/secret")
    public String addSecret(@RequestBody SecretRequest request) {
        Map<String, String> data = new HashMap<>();
        data.put(request.getSecretName(), request.getSecret());

        Versioned.Metadata createResponse = vaultOperations
                .opsForVersionedKeyValue(SECRET_STORAGE_NAME)
                .put(request.getSecretName(), data);

        log.debug("Secret written successfully {}", createResponse);

        return "Secret added successfully";
    }

    @GetMapping("/secret")
    public String getSecret(@RequestBody SecretRequest request) {
        log.debug("Reading secret");
        Versioned<Map<String, Object>> readResponse = vaultOperations
                .opsForVersionedKeyValue(SECRET_STORAGE_NAME)
                .get(request.getSecretName());

        String secret = "";
        if (readResponse != null && readResponse.hasData()) {
            secret = (String) readResponse.getData().get(request.getSecretName());
        }

        return secret;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class SecretRequest {
        private String secretName;
        private String secret;
    }
}
