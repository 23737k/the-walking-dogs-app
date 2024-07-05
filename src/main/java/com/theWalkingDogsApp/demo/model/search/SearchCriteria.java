package com.theWalkingDogsApp.demo.model.search;

import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import java.util.List;

public interface SearchCriteria {
  List<CareGiver> filter(List<CareGiver> careGivers);
}
