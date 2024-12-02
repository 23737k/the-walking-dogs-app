package com.theWalkingDogsApp.demo.repository.specification;

import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.pet.DogSize;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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
    if(filter.maxRate() != null || filter.minRate() != null)
      spec = spec.and(rateBetween(filter.maxRate(), filter.minRate()));
    if(filter.dogSizes()!=null)
      spec = spec.and(allowDogSizes(filter.dogSizes()));
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

  private Specification<DogWalker> rateBetween(Integer max, Integer min){
    return (root, criteria, builder) -> {
      Predicate predicate;
      if(max != null){
        if (min!=null){
          predicate = builder.between(root.get("ratePerWalk"), min, max);
        }
        else
          predicate = builder.lessThanOrEqualTo(root.get("ratePerWalk"), max);
      }
      else
        predicate = builder.greaterThanOrEqualTo(root.get("ratePerWalk"), min);
      return predicate;
    };
  }

  private Specification<DogWalker> allowDogSizes(List<DogSize> dogSizes){
    return (root, criteria, builder) -> {
     Subquery<Long> subquery = criteria.subquery(Long.class);
     Root<DogWalker> baseRoot = subquery.from(DogWalker.class);
     var joinRoot = baseRoot.join("dogSizesAllowed");
     Predicate predicate = builder.and(
         builder.equal(root.get("id"),baseRoot.get("id")),
         joinRoot.in(dogSizes));
     subquery.select(builder.countDistinct(joinRoot)).where(predicate);
     return builder.equal(subquery,dogSizes.size());
    };
  }

//  private Specification<Dog> radiusWithin(int radius){
//
//  }

}
