package org.rgr.test.cellphoneusagetest.repositories;

import org.rgr.test.cellphoneusagetest.model.csv.CellPhone;

import java.util.List;

public interface CellPhoneRepository {
  List<CellPhone> findAll();
}
