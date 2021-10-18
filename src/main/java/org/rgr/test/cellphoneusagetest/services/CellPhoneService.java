package org.rgr.test.cellphoneusagetest.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rgr.test.cellphoneusagetest.model.csv.CellPhone;
import org.rgr.test.cellphoneusagetest.repositories.CellPhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CellPhoneService {
  private final CellPhoneRepository cellPhoneRepository;

  public List<CellPhone> findAll() {
    return cellPhoneRepository.findAll();
  }
}
