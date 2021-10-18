package org.rgr.test.cellphoneusagetest.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;
import org.rgr.test.cellphoneusagetest.repositories.CellPhoneMonthlyUsageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CellPhoneMonthlyUsageService {
  private final CellPhoneMonthlyUsageRepository cellPhoneMonthlyUsageRepository;

  public List<CellPhoneMonthlyUsage> findAll() {
    return cellPhoneMonthlyUsageRepository.findAll();
  }
}
