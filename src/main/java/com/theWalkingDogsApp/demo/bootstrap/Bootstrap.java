package com.theWalkingDogsApp.demo.bootstrap;

import com.github.javafaker.Faker;
import com.theWalkingDogsApp.demo.model.careGiver.DogWalker;
import com.theWalkingDogsApp.demo.model.careGiver.BasicInfo;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.model.schedule.DailyAvailability;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.walkRequest.DogSize;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class Bootstrap {
    private final EntityManager em;
    private final CareGiverRepo careGiverRepo;

    @Transactional
    public void init(){
        if(emptyDB()){
            loadCareGivers();
        }

    }

    @Transactional
    protected void loadCareGivers(){
        careGiverRepo.saveAll(createCareGiver(30));
    }

    private List<CareGiver> createCareGiver(int n){
        Faker faker = new Faker(new Locale("es", "AR"));
        List<CareGiver> careGivers = new ArrayList<>();
        for(int i = 0 ; i<n; i++){
            BasicInfo basicInfo = BasicInfo.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .email(faker.internet().emailAddress())
                    .build();

            DogWalker dogWalker = DogWalker.builder()
                .schedule(new Schedule(createDailyAvailabilities(),null))
                .dogSizesAllowed(createAllowedDogSizes())
                .ratePerWalk(new Random().nextInt(5,60))
                .isActive(Boolean.TRUE)
                .serviceRadius(new Random().nextInt(5,15))
                .build();

            CareGiver careGiver = CareGiver.builder()
                    .basicInfo(basicInfo)
                    .bio(faker.lorem().paragraph())
                    .dogWalker(dogWalker)
                    .build();

            careGivers.add(careGiver);
        }
        return careGivers;
    }

    private List<DailyAvailability> createDailyAvailabilities(){
        List<WeekDay> weekDays = EnumSet.allOf(WeekDay.class).stream().toList();
        List<TimeSlot> timeSlots = EnumSet.allOf(TimeSlot.class).stream().toList();
        List<DailyAvailability> dailyAvailabilities = new ArrayList<>();
        for(int i = 0 ; i< 7; i++){
            int rnd1 = new Random().nextInt(7);
            int rnd2 = new Random().nextInt(3);
            dailyAvailabilities.add(new DailyAvailability(weekDays.get(rnd1), Set.of(timeSlots.get(rnd2))));
        }
        if(dailyAvailabilities.isEmpty()){
            dailyAvailabilities.add(new DailyAvailability(weekDays.get(0), Set.of(timeSlots.get(0))));
        }
      return dailyAvailabilities;
    }

    private Set<DogSize> createAllowedDogSizes(){
        List<DogSize> dogSizes = EnumSet.allOf(DogSize.class).stream().toList();
        Set<DogSize> allowedDogSizes = new HashSet<>();
        for(int i = 0 ; i< 4; i++){
            int rnd = new Random().nextInt(4);
            allowedDogSizes.add(dogSizes.get(rnd));
        }
        if(allowedDogSizes.isEmpty())
            allowedDogSizes.add(dogSizes.get(new Random().nextInt(4)));
        return allowedDogSizes;
    }



    private boolean emptyDB(){
        List<String> tables = List.of("CareGiver");
        for(String t : tables){
            Query query = em.createQuery("SELECT COUNT(*) FROM " + t);
            Long count = (Long) query.getSingleResult();
            if(count>0)
                return false;
        }
        return true;
    }

}
