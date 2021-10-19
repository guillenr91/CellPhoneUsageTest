package org.rgr.test.cellphoneusagetest.services;

import org.rgr.test.cellphoneusagetest.model.UsageAggregate;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhone;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;
import org.rgr.test.cellphoneusagetest.model.report.Detail;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DetailService {

  public List<Detail> mapAsDetails(
      final List<CellPhoneMonthlyUsage> cellPhoneMonthlyUsageList,
      final Map<Long, CellPhone> cellPhoneByEmployeeId) {
    return aggregateByEmployeeIdAndMonth(cellPhoneMonthlyUsageList).entrySet().stream()
        .map(
            monthlyUsageByEmployeeId ->
                mapAsDetail(
                    cellPhoneByEmployeeId.get(monthlyUsageByEmployeeId.getKey()),
                    monthlyUsageByEmployeeId.getValue()))
        .collect(Collectors.toList());
  }

  private Map<Long, Map<Month, UsageAggregate>> aggregateByEmployeeIdAndMonth(
      List<CellPhoneMonthlyUsage> cellPhoneMonthlyUsageList) {
    return cellPhoneMonthlyUsageList.stream()
        .collect(
            Collectors.toMap(
                CellPhoneMonthlyUsage::getEmployeeId,
                cellPhoneMonthlyUsage ->
                    Map.of(
                        cellPhoneMonthlyUsage.getMonth(),
                        UsageAggregate.builder()
                            .totalMinutes(cellPhoneMonthlyUsage.getTotalMinutes())
                            .totalData(cellPhoneMonthlyUsage.getTotalData())
                            .build()),
                this::mergeUsageAggregateByMonthMaps));
  }

  private Map<Month, UsageAggregate> mergeUsageAggregateByMonthMaps(
      Map<Month, UsageAggregate> usageAggregateByMonth1,
      Map<Month, UsageAggregate> usageAggregateByMonth2) {
    return Stream.concat(
            usageAggregateByMonth1.entrySet().stream(), usageAggregateByMonth2.entrySet().stream())
        .collect(
            Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (aggregate1, aggregate2) ->
                    UsageAggregate.builder()
                        .totalMinutes(aggregate1.getTotalMinutes() + aggregate2.getTotalMinutes())
                        .totalData(aggregate1.getTotalData() + aggregate2.getTotalData())
                        .build()));
  }

  private Detail mapAsDetail(
      final CellPhone cellPhone, final Map<Month, UsageAggregate> usageByMonthMap) {

    Map<Month, Double> minutesUsagePerMonth = new HashMap<>();
    Map<Month, Double> dataUsagePerMonth = new HashMap<>();
    for (Map.Entry<Month, UsageAggregate> usageByMonth : usageByMonthMap.entrySet()) {
      minutesUsagePerMonth.put(usageByMonth.getKey(), usageByMonth.getValue().getTotalMinutes());
      dataUsagePerMonth.put(usageByMonth.getKey(), usageByMonth.getValue().getTotalData());
    }

    return Detail.builder()
        .employeeId(cellPhone.getEmployeeId())
        .employeeName(cellPhone.getEmployeeName())
        .model(cellPhone.getModel())
        .purchaseDate(cellPhone.getPurchaseDate())
        .minutesUsagePerMonth(minutesUsagePerMonth)
        .dataUsagePerMonth(dataUsagePerMonth)
        .build();
  }
}
