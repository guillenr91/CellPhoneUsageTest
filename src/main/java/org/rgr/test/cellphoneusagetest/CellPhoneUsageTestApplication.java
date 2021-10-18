package org.rgr.test.cellphoneusagetest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhone;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;
import org.rgr.test.cellphoneusagetest.services.CellPhoneMonthlyUsageService;
import org.rgr.test.cellphoneusagetest.services.CellPhoneService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class CellPhoneUsageTestApplication implements ApplicationRunner {
  private final CellPhoneService cellPhoneService;
  private final CellPhoneMonthlyUsageService cellPhoneMonthlyUsageService;

  public static void main(String[] args) {
    SpringApplication.run(CellPhoneUsageTestApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    final List<CellPhone> cellPhoneList = cellPhoneService.findAll();
    final List<CellPhoneMonthlyUsage> cellPhoneMonthlyUsageList =
        cellPhoneMonthlyUsageService.findAll();

    log.info(
        String.format(
            "[CellPhone]%n%s%n%n",
            cellPhoneList.stream().map(Objects::toString).collect(Collectors.joining(",\r\n"))));

    log.info(
        String.format(
            "[CellPhoneMonthlyUsage]%n%s%n%n",
            cellPhoneMonthlyUsageList.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(",\r\n"))));
  }
}
