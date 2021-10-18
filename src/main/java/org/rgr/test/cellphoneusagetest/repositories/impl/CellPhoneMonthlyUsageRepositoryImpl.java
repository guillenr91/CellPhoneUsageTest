package org.rgr.test.cellphoneusagetest.repositories.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.RequiredArgsConstructor;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;
import org.rgr.test.cellphoneusagetest.repositories.CellPhoneMonthlyUsageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CellPhoneMonthlyUsageRepositoryImpl implements CellPhoneMonthlyUsageRepository {
  private final ObjectReader cellPhoneMonthlyUsageCsvReader;

  @Value("${csv.cellPhoneMonthlyUsage.file}")
  private String cellPhoneMonthlyUsageCsvFile;

  @Override
  public List<CellPhoneMonthlyUsage> findAll() {
    final File csvFile = Paths.get(cellPhoneMonthlyUsageCsvFile).toFile();
    try (final MappingIterator<CellPhoneMonthlyUsage> iterator =
        cellPhoneMonthlyUsageCsvReader.readValues(csvFile)) {
      return iterator.readAll();
    } catch (IOException e) {
      throw new RuntimeException(
          String.format(
              "Could NOT read CellPhoneMonthlyUsage object from: %s! Reason %s -> %s",
              csvFile, e.getClass().getSimpleName(), e.getMessage()),
          e);
    }
  }
}
