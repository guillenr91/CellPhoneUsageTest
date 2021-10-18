package org.rgr.test.cellphoneusagetest.services;

import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;
import org.rgr.test.cellphoneusagetest.model.report.Header;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HeaderService {
  public Header build(final List<CellPhoneMonthlyUsage> cellPhoneMonthlyUsageList) {
    double totalMinutes = 0;
    double totalData = 0;
    Set<Long> phoneNumbers = new HashSet<>();
    for (CellPhoneMonthlyUsage monthlyUsage : cellPhoneMonthlyUsageList) {
      totalMinutes += monthlyUsage.getTotalMinutes();
      totalData += monthlyUsage.getTotalData();
      phoneNumbers.add(monthlyUsage.getEmployeeId());
    }

    return Header.builder()
        .reportRunDate(LocalDate.now())
        .numberOfPhones(phoneNumbers.size())
        .totalMinutes(totalMinutes)
        .averageMinutes(totalMinutes / cellPhoneMonthlyUsageList.size())
        .totalData(totalData)
        .averageData(totalData / cellPhoneMonthlyUsageList.size())
        .build();
  }
}
