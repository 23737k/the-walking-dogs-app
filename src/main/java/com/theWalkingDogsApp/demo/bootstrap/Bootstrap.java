package com.theWalkingDogsApp.demo.bootstrap;

import com.github.javafaker.Faker;
import com.theWalkingDogsApp.demo.model.dogWalker.DogWalker;
import com.theWalkingDogsApp.demo.model.pet.DogSize;
import com.theWalkingDogsApp.demo.model.schedule.DailyAvailability;
import com.theWalkingDogsApp.demo.model.schedule.Schedule;
import com.theWalkingDogsApp.demo.model.schedule.TimeSlot;
import com.theWalkingDogsApp.demo.model.schedule.WeekDay;
import com.theWalkingDogsApp.demo.model.user.User;
import com.theWalkingDogsApp.demo.model.user.UserProfile;
import com.theWalkingDogsApp.demo.repository.UserRepository;
import com.theWalkingDogsApp.demo.service.DogWalkerService;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Bootstrap {
    private final DogWalkerService dogWalkerService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Value("${bootstrap.number-of-dog-walkers}")
    private int numberOfDogWalkers;
    private final Faker faker = new Faker(new Locale("es", "AR"));

    @Transactional
    public void init(){
        if(emptyDB()){
            loadUsers(numberOfDogWalkers);
        }

    }

    @Transactional
    public void loadUsers(int n){
        List<User> users = new ArrayList<>();

        for(int i = 0 ; i<n; i++){
            User user = createUser();
            user.setDogWalker(createDogWalker());
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    @Transactional
    public User createUser() {
        UserProfile profile = UserProfile.builder()
            .firstname(faker.name().firstName())
            .lastname(faker.name().lastName())
            .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
            .phoneNumber(faker.phoneNumber().phoneNumber())
            .build();

      return new User(faker.internet().emailAddress(), passwordEncoder.encode("P@ssw0rd!"),profile);
    }

    @Transactional
    public DogWalker createDogWalker() {
        return DogWalker.builder()
            .schedule(new Schedule(createDailyAvailabilities(), null))
            .dogSizesAllowed(createAllowedDogSizes())
            .ratePerWalk(new Random().nextInt(5, 60))
            .isActive(Boolean.TRUE)
            .serviceRadius(new Random().nextInt(5, 15))
            .bio("")
            .build();
    }

    @Transactional
    public List<DailyAvailability> createDailyAvailabilities(){
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

    @Transactional
    public Set<DogSize> createAllowedDogSizes(){
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
       return dogWalkerService.isEmpty();
    }

}
