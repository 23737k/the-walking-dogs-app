package com.theWalkingDogsApp.demo.model.search;

import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchService {
  private final CareGiverRepo careGiverRepo;

  public List<CareGiver> searchCareGivers(SearchCriteria searchCriteria) {
    //TODO cambiar por queries?
    return searchCriteria.filter(careGiverRepo.findAll());
  }

}
