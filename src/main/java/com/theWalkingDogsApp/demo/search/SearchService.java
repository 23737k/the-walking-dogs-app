package com.theWalkingDogsApp.demo.search;

import com.theWalkingDogsApp.demo.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
  private final CareGiverRepo careGiverRepo;

  public List<CareGiver> searchCareGivers(SearchCriteria searchCriteria) {
    //TODO cambiar por queries?
    return searchCriteria.filter(careGiverRepo.findAll());
  }

}
