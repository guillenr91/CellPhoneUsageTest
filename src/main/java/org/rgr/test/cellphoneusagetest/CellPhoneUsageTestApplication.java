package org.rgr.test.cellphoneusagetest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhone;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;
import org.rgr.test.cellphoneusagetest.model.report.Detail;
import org.rgr.test.cellphoneusagetest.model.report.Header;
import org.rgr.test.cellphoneusagetest.services.CellPhoneMonthlyUsageService;
import org.rgr.test.cellphoneusagetest.services.CellPhoneService;
import org.rgr.test.cellphoneusagetest.services.DetailService;
import org.rgr.test.cellphoneusagetest.services.HeaderService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class CellPhoneUsageTestApplication implements ApplicationRunner {
  private final CellPhoneService cellPhoneService;
  private final CellPhoneMonthlyUsageService cellPhoneMonthlyUsageService;
  private final HeaderService headerService;
  private final DetailService detailService;

  public static void main(String[] args) {
    SpringApplication.run(CellPhoneUsageTestApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    final List<CellPhone> cellPhoneList = cellPhoneService.findAll();
    final List<CellPhoneMonthlyUsage> cellPhoneMonthlyUsageList =
        cellPhoneMonthlyUsageService.findAll();

    final Header header = headerService.build(cellPhoneMonthlyUsageList);
    final List<Detail> detailList =
        detailService.mapAsDetails(
            cellPhoneMonthlyUsageList,
            cellPhoneList.stream()
                .collect(Collectors.toMap(CellPhone::getEmployeeId, Function.identity())));

    log.info(String.format("[Header Section]%n%s", header));
    log.info(
        String.format(
            "[Details Section]%n%s",
            detailList.stream().map(Objects::toString).collect(Collectors.joining(",\r\n"))));
  }
}
