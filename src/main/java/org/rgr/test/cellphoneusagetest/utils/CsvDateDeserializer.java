package org.rgr.test.cellphoneusagetest.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CsvDateDeserializer extends StdDeserializer<LocalDate> {
  private static final String[] DATE_FORMATS = {"MM/dd/yyyy", "M/dd/yyyy", "MM/d/yyyy", "M/d/yyyy"};

  protected CsvDateDeserializer() {
    this(null);
  }

  protected CsvDateDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    final JsonNode node = jp.getCodec().readTree(jp);
    final String date = node.asText();

    for (String dateFormat : DATE_FORMATS) {
      try {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
      } catch (DateTimeParseException ignored) {
      }
    }

    throw new JsonParseException(
        jp,
        String.format(
            "Cannot deserialize value of type `java.time.LocalDate` from String: '%s'", date));
  }
}
