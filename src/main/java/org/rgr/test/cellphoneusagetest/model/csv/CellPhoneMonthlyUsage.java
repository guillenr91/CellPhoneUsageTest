package org.rgr.test.cellphoneusagetest.model.csv;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.rgr.test.cellphoneusagetest.utils.CsvDateDeserializer;

import java.time.LocalDate;
import java.time.Month;

@Data
@JsonPropertyOrder({"employeeId", "date", "totalMinutes", "totalData"})
public class CellPhoneMonthlyUsage {
  long employeeId;

  @JsonDeserialize(using = CsvDateDeserializer.class)
  LocalDate date;

  double totalMinutes;
  double totalData;

  public Month getMonth() {
    return date.getMonth();
  }
}
