package org.rgr.test.cellphoneusagetest.config;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhone;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvConfig {
  @Bean
  public ObjectReader cellPhoneCsvReader() {
    final CsvMapper csvMapper = new CsvMapper();
    csvMapper.findAndRegisterModules();
    final CsvSchema cellPhoneCsvSchema = CsvSchema.emptySchema().withHeader();
    return csvMapper.readerFor(CellPhone.class).with(cellPhoneCsvSchema);
  }

  @Bean
  public ObjectReader cellPhoneMonthlyUsageCsvReader() {
    final CsvMapper csvMapper = new CsvMapper();
    csvMapper.findAndRegisterModules();
    final CsvSchema cellPhoneCsvSchema = CsvSchema.emptySchema().withHeader();
    return csvMapper.readerFor(CellPhoneMonthlyUsage.class).with(cellPhoneCsvSchema);
  }
}
