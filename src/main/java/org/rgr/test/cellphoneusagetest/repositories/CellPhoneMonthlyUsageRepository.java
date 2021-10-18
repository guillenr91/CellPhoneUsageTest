package org.rgr.test.cellphoneusagetest.repositories;

import org.rgr.test.cellphoneusagetest.model.csv.CellPhoneMonthlyUsage;

import java.util.List;

public interface CellPhoneMonthlyUsageRepository {
  List<CellPhoneMonthlyUsage> findAll();
}
