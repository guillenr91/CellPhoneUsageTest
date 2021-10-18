package org.rgr.test.cellphoneusagetest.repositories.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.RequiredArgsConstructor;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhone;
import org.rgr.test.cellphoneusagetest.repositories.CellPhoneRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CellPhoneRepositoryImpl implements CellPhoneRepository {
  private final ObjectReader cellPhoneCsvReader;

  @Value("${csv.cellPhone.file}")
  private String cellPhoneCsvFileName;

  @Override
  public List<CellPhone> findAll() {
    final File csvFile = Paths.get(cellPhoneCsvFileName).toFile();
    try (final MappingIterator<CellPhone> iterator = cellPhoneCsvReader.readValues(csvFile)) {
      return iterator.readAll();
    } catch (IOException e) {
      throw new RuntimeException(
          String.format(
              "Could NOT read CellPhone object from: %s! Reason %s -> %s",
              csvFile, e.getClass().getSimpleName(), e.getMessage()),
          e);
    }
  }
}
