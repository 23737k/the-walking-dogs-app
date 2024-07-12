package com.theWalkingDogsApp.demo.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.theWalkingDogsApp.demo.filter.CareGiverFilter;
import com.theWalkingDogsApp.demo.filter.CareGiverPredicateBuilder;
import com.theWalkingDogsApp.demo.model.careGiver.CareGiver;
import com.theWalkingDogsApp.demo.model.careGiver.QCareGiver;
import com.theWalkingDogsApp.demo.model.careGiver.QDogWalker;
import com.theWalkingDogsApp.demo.model.schedule.QDailyAvailability;
import com.theWalkingDogsApp.demo.model.schedule.QSchedule;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CareGiverCustom {
    private final JPAQueryFactory queryFactory;
    public CareGiverCustom(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<CareGiver> findCareGivers(CareGiverFilter filter){
        BooleanBuilder predicate = CareGiverPredicateBuilder.buildPredicate(filter);
        QCareGiver careGiver = QCareGiver.careGiver;
        QSchedule schedule = QSchedule.schedule;
        QDogWalker dogWalker = QDogWalker.dogWalker;
        QDailyAvailability dailyAvailability = QDailyAvailability.dailyAvailability;
        return queryFactory.selectFrom(QCareGiver.careGiver).leftJoin(careGiver.dogWalker,dogWalker)
                .leftJoin(dogWalker.schedule, schedule).leftJoin(schedule.dailyAvailabilities, dailyAvailability).where(predicate).fetch();
    }
}
