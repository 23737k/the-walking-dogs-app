package com.theWalkingDogsApp.demo.bootstrap;

import com.github.javafaker.Faker;
import com.theWalkingDogsApp.demo.model.careGiver.BasicInfo;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.repository.CareGiverRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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
        careGiverRepo.saveAll(createCareGiver(10));
    }

    private List<CareGiver> createCareGiver(int n){
        Faker faker = new Faker(new Locale("es", "AR"));
        List<CareGiver> careGivers = new ArrayList<>();
        for(int i = 0 ; i<n; i++){
            BasicInfo basicInfo = BasicInfo.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .phoneNumber(faker.phoneNumber().toString())
                    .email(faker.internet().emailAddress())
                    .build();
            CareGiver careGiver = CareGiver.builder()
                    .basicInfo(basicInfo)
                    .bio(faker.lorem().toString())
                    .build();

            careGivers.add(careGiver);
        }
        return careGivers;
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
