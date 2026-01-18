package com.example.cornellnote.api.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.InputStream;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

public abstract class ApiTestSupport {
  @Autowired protected MockMvc mockMvc;

  @Autowired protected ObjectMapper objectMapper;

  protected ResultActions performGet(String urlTemplate, Object... uriVars) throws Exception {
    return mockMvc.perform(
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(
                urlTemplate, uriVars)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performDelete(String urlTemplate, Object... uriVars) throws Exception {
    return mockMvc.perform(
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(
                urlTemplate, uriVars)
            .accept(MediaType.APPLICATION_JSON));
  }

  protected ResultActions performPostJson(String urlTemplate, Object body, Object... uriVars)
      throws Exception {
    var request =
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(
                urlTemplate, uriVars)
            .accept(MediaType.APPLICATION_JSON);
    if (body != null) {
      request =
          request
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(body));
    }
    return mockMvc.perform(request);
  }

  protected ResultActions performPutJson(String urlTemplate, Object body, Object... uriVars)
      throws Exception {
    var request =
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put(
                urlTemplate, uriVars)
            .accept(MediaType.APPLICATION_JSON);
    if (body != null) {
      request =
          request
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(body));
    }
    return mockMvc.perform(request);
  }

  protected String loadJson(String path) throws IOException {
    try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
      Assertions.assertThat(input).as("resource should exist: %s", path).isNotNull();
      return new String(input.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
    }
  }

  protected void assertResponseMatches(String expectedPath, String actualJson) throws Exception {
    String expectedJson = loadJson(expectedPath);
    JsonNode expectedNode = objectMapper.readTree(expectedJson);
    JsonNode actualNode = objectMapper.readTree(actualJson);
    replaceJsonUnitIgnore(expectedNode, actualNode);
    org.skyscreamer.jsonassert.JSONAssert.assertEquals(
        expectedNode.toString(), actualNode.toString(), true);
  }

  private void replaceJsonUnitIgnore(JsonNode expectedNode, JsonNode actualNode) {
    Assertions.assertThat(actualNode).as("actual JSON should exist").isNotNull();

    if (expectedNode.isObject()) {
      ObjectNode objectNode = (ObjectNode) expectedNode;
      java.util.Iterator<String> fields = objectNode.fieldNames();
      while (fields.hasNext()) {
        String field = fields.next();
        JsonNode expectedChild = objectNode.get(field);
        Assertions.assertThat(actualNode.has(field)).as("missing field '%s'", field).isTrue();
        JsonNode actualChild = actualNode.get(field);
        if (expectedChild.isTextual() && "${json-unit.ignore}".equals(expectedChild.textValue())) {
          objectNode.set(field, actualChild);
        } else {
          if (expectedChild.isArray() && actualChild.isArray()) {
            maybeSortByField(
                (com.fasterxml.jackson.databind.node.ArrayNode) expectedChild,
                (com.fasterxml.jackson.databind.node.ArrayNode) actualChild,
                "tagId");
          }
          replaceJsonUnitIgnore(expectedChild, actualChild);
        }
      }
    }

    if (expectedNode.isArray()) {
      Assertions.assertThat(actualNode.isArray())
          .as("expected array but was %s", actualNode.getNodeType())
          .isTrue();
      Assertions.assertThat(actualNode.size())
          .as("array size mismatch")
          .isEqualTo(expectedNode.size());
      if (expectedNode instanceof com.fasterxml.jackson.databind.node.ArrayNode
          && actualNode instanceof com.fasterxml.jackson.databind.node.ArrayNode) {
        maybeSortByField(
            (com.fasterxml.jackson.databind.node.ArrayNode) expectedNode,
            (com.fasterxml.jackson.databind.node.ArrayNode) actualNode,
            "tagId");
      }
      for (int i = 0; i < expectedNode.size(); i++) {
        replaceJsonUnitIgnore(expectedNode.get(i), actualNode.get(i));
      }
    }
  }

  private void maybeSortByField(
      com.fasterxml.jackson.databind.node.ArrayNode expectedArray,
      com.fasterxml.jackson.databind.node.ArrayNode actualArray,
      String field) {
    boolean shouldSort = true;
    for (JsonNode element : expectedArray) {
      if (!element.isObject() || !element.has(field)) {
        shouldSort = false;
        break;
      }
    }
    if (shouldSort) {
      for (JsonNode element : actualArray) {
        if (!element.isObject() || !element.has(field)) {
          shouldSort = false;
          break;
        }
      }
    }
    if (shouldSort) {
      java.util.Comparator<JsonNode> comparator =
          java.util.Comparator.comparing(node -> node.get(field).asText());
      sortArray(expectedArray, comparator);
      sortArray(actualArray, comparator);
    }
  }

  private void sortArray(
      com.fasterxml.jackson.databind.node.ArrayNode array,
      java.util.Comparator<JsonNode> comparator) {
    java.util.List<JsonNode> nodes = new java.util.ArrayList<>();
    array.forEach(nodes::add);
    nodes.sort(comparator);
    array.removeAll();
    nodes.forEach(array::add);
  }
}
