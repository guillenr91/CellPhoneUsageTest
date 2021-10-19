package org.rgr.test.cellphoneusagetest.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UsageAggregate {
  double totalMinutes;
  double totalData;
}
