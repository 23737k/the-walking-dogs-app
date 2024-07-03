package com.theWalkingDogsApp.demo.search;

import com.theWalkingDogsApp.demo.careGiver.CareGiver;
import java.util.List;

public interface SearchCriteria {
  List<CareGiver> filter(List<CareGiver> careGivers);
}
