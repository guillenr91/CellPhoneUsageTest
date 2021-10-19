package org.rgr.test.cellphoneusagetest.model.report;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@Value
@Builder
public class Detail {
  long employeeId;
  String employeeName;
  String model;
  LocalDate purchaseDate;
  Map<Month, Double> minutesUsagePerMonth;
  Map<Month, Double> dataUsagePerMonth;
}
