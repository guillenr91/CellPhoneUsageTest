package org.rgr.test.cellphoneusagetest.model.csv;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonPropertyOrder({"employeeId", "employeeName", "purchaseDate", "model"})
public class CellPhone {
  long employeeId;
  String employeeName;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
  LocalDate purchaseDate;

  String model;
}
