package org.rgr.test.cellphoneusagetest.model.report;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Header {
  LocalDate reportRunDate;
  long numberOfPhones;
  double totalMinutes;
  double totalData;
  double averageMinutes;
  double averageData;
}
