package com.theWalkingDogsApp.demo.repository.specification;

import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DogWalkerSpec {

  public Specification<DogWalker> getAllSpecification(DogWalkerFilter filter){
    Specification<DogWalker> spec = Specification.where(null);
    if(filter.active() != null)
      spec = spec.and(active(filter.active()));
    if(filter.weekDays() != null)
      spec = spec.and(isAvailableOnWeekDays(filter.weekDays()));
    if(filter.timeSlots()!= null)
      spec = spec.and(isAvailableOnTimeSlots(filter.timeSlots()));
    return spec;
  }

  private Specification<DogWalker> active(boolean active){
    return (root, criteria, builder) -> builder.equal(root.get("isActive"), active);
  }

  private Specification<DogWalker> isAvailableOnWeekDays(List<WeekDay> weekDays){
    return (root, criteria, builder) -> {
      Subquery<Long> subquery = criteria.subquery(Long.class);
      Root<DogWalker> baseRoot = subquery.from(DogWalker.class);
      var joinRoot = baseRoot.join("schedule").join("dailyAvailabilities");

      Predicate availableOnDay = builder.and(builder.equal(root.get("id"), baseRoot.get("id")),
          joinRoot.get("weekDay").in(weekDays));

      subquery.select(builder.count(joinRoot)).where(availableOnDay);

      return builder.equal(subquery,weekDays.size());
    };
  }

  private Specification<DogWalker> isAvailableOnTimeSlots(List<TimeSlot> timeSlots) {
    return (root, criteria, builder) -> {
      Subquery<Long> subquery = criteria.subquery(Long.class);
      Root<DogWalker> baseRoot = subquery.from(DogWalker.class);
      var joinRoot = baseRoot.join("schedule")
          .join("dailyAvailabilities")
          .join("timeSlots");

      Predicate isAvailableOnTimeSlots = builder.and(builder.equal(root.get("id"),baseRoot.get("id")),
          joinRoot.in(timeSlots));

      subquery.select(builder.countDistinct(joinRoot)).where(isAvailableOnTimeSlots);

      return builder.greaterThanOrEqualTo(subquery, (long) timeSlots.size());
    };
  }


}
