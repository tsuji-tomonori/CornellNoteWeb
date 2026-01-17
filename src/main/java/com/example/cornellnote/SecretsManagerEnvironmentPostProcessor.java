package com.example.cornellnote;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public class SecretsManagerEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
  private static final String SECRET_ARN_ENV = "DB_SECRET_ARN";
  private static final String REGION_ENV = "AWS_REGION";
  private static final String DEFAULT_PORT = "5432";
  private static final String PROPERTY_SOURCE_NAME = "dbSecret";
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
    String secretArn = environment.getProperty(SECRET_ARN_ENV);
    if (secretArn == null || secretArn.isBlank()) {
      return;
    }

    String regionName = environment.getProperty(REGION_ENV);
    if (regionName == null || regionName.isBlank()) {
      throw new IllegalStateException("AWS_REGION is required when DB_SECRET_ARN is set");
    }

    Map<String, Object> properties = loadDatabaseProperties(secretArn, regionName);
    PropertySource<?> propertySource = new MapPropertySource(PROPERTY_SOURCE_NAME, properties);
    environment.getPropertySources().addFirst(propertySource);
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  private Map<String, Object> loadDatabaseProperties(String secretArn, String regionName) {
    try (SecretsManagerClient client = SecretsManagerClient.builder()
        .region(Region.of(regionName))
        .build()) {
      GetSecretValueResponse response = client.getSecretValue(
          GetSecretValueRequest.builder().secretId(secretArn).build()
      );
      String secretString = response.secretString();
      if (secretString == null || secretString.isBlank()) {
        throw new IllegalStateException("Secret value is empty");
      }
      JsonNode secret = objectMapper.readTree(secretString);
      String username = readText(secret, "username");
      String password = readText(secret, "password");
      String host = readText(secret, "host");
      String port = readOptionalText(secret, "port", DEFAULT_PORT);
      String dbName = readText(secret, "dbname");

      Map<String, Object> properties = new HashMap<>();
      properties.put("spring.datasource.url", buildJdbcUrl(host, port, dbName));
      properties.put("spring.datasource.username", username);
      properties.put("spring.datasource.password", password);
      return properties;
    } catch (SecretsManagerException | IOException ex) {
      throw new IllegalStateException("Failed to load database secret", ex);
    }
  }

  private String buildJdbcUrl(String host, String port, String dbName) {
    return "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
  }

  private String readText(JsonNode secret, String field) {
    JsonNode node = secret.get(field);
    if (node == null || node.asText().isBlank()) {
      throw new IllegalStateException("Missing secret field: " + field);
    }
    return node.asText();
  }

  private String readOptionalText(JsonNode secret, String field, String fallback) {
    JsonNode node = secret.get(field);
    if (node == null || node.asText().isBlank()) {
      return fallback;
    }
    return node.asText();
  }
}
