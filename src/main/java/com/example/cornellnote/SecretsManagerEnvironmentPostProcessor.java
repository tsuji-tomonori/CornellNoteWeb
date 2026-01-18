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
  public void postProcessEnvironment(
      final ConfigurableEnvironment environment, final SpringApplication application) {
    final String secretArn = environment.getProperty(SECRET_ARN_ENV);
    if (secretArn == null || secretArn.isBlank()) {
      return;
    }

    final String regionName = environment.getProperty(REGION_ENV);
    if (regionName == null || regionName.isBlank()) {
      throw new IllegalStateException("AWS_REGION is required when DB_SECRET_ARN is set");
    }

    final Map<String, Object> properties = loadDatabaseProperties(secretArn, regionName);
    final PropertySource<?> propertySource =
        new MapPropertySource(PROPERTY_SOURCE_NAME, properties);
    addPropertySource(environment, propertySource);
  }

  @Override
  public int getOrder() {
    return HIGHEST_PRECEDENCE;
  }

  private void addPropertySource(
      final ConfigurableEnvironment environment, final PropertySource<?> propertySource) {
    environment.getPropertySources().addFirst(propertySource);
  }

  private Map<String, Object> loadDatabaseProperties(
      final String secretArn, final String regionName) {
    try (SecretsManagerClient client =
        SecretsManagerClient.builder().region(Region.of(regionName)).build()) {
      final GetSecretValueResponse response =
          client.getSecretValue(GetSecretValueRequest.builder().secretId(secretArn).build());
      final String secretString = response.secretString();
      if (secretString == null || secretString.isBlank()) {
        throw new IllegalStateException("Secret value is empty");
      }
      final JsonNode secret = objectMapper.readTree(secretString);
      final String username = readText(secret, "username");
      final String password = readText(secret, "password");
      final String host = readText(secret, "host");
      final String port = readOptionalText(secret, "port", DEFAULT_PORT);
      final String dbName = readText(secret, "dbname");

      final Map<String, Object> properties = new HashMap<>();
      properties.put("spring.datasource.url", buildJdbcUrl(host, port, dbName));
      properties.put("spring.datasource.username", username);
      properties.put("spring.datasource.password", password);
      return properties;
    } catch (SecretsManagerException | IOException ex) {
      throw new IllegalStateException("Failed to load database secret", ex);
    }
  }

  private String buildJdbcUrl(final String host, final String port, final String dbName) {
    return "jdbc:postgresql://" + host + ":" + port + "/" + dbName;
  }

  private String readText(final JsonNode secret, final String field) {
    final JsonNode node = secret.get(field);
    if (node == null || node.asText().isBlank()) {
      throw new IllegalStateException("Missing secret field: " + field);
    }
    return node.asText();
  }

  private String readOptionalText(
      final JsonNode secret, final String field, final String fallback) {
    final JsonNode node = secret.get(field);
    String value = fallback;
    if (node != null && !node.asText().isBlank()) {
      value = node.asText();
    }
    return value;
  }
}
