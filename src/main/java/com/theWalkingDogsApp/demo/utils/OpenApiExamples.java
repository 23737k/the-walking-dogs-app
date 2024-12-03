package com.theWalkingDogsApp.demo.utils;


public class OpenApiExamples {

  public final static String recurringWalkReq = "{\"type\":\"recurring\",\"pets\":[{\"name\":\"Sol\",\"breed\":\"mixed breed\",\"weighInKg\":20,\"sex\":\"FEMALE\",\"age\":8},{\"name\":\"Lola\",\"breed\":\"mixed breed\",\"weighInKg\":10,\"sex\":\"FEMALE\",\"age\":12}],\"phoneNumber\":\"123456789\",\"message\":\"Las chicas comen 2 veces al dia\",\"walksPerWeekDays\":[{\"weekDay\":\"MONDAY\",\"walkingHours\":[\"15:00:00\",\"21:00:00\"]},{\"weekDay\":\"TUESDAY\",\"walkingHours\":[\"15:00:00\",\"21:00:00\"]}],\"startOfService\":\"2025-11-28\",\"endOfService\":\"2025-12-30\",\"dogWalkerId\":2}\n";

  public final static String oneTimeWalkReq = "{\"type\":\"one-time\",\"pets\":[{\"name\":\"Sol\",\"breed\":\"mixed breed\",\"weighInKg\":20,\"sex\":\"FEMALE\",\"age\":8},{\"name\":\"Lola\",\"breed\":\"mixed breed\",\"weighInKg\":10,\"sex\":\"FEMALE\",\"age\":12}],\"phoneNumber\":\"123456789\",\"message\":\"Las chicas comen 2 veces al dia\",\"walksPerDate\":[{\"date\":\"2025-04-01\",\"walkingHours\":[\"15:00:00\",\"21:00:00\"]},{\"date\":\"2025-04-02\",\"walkingHours\":[\"15:00:00\",\"21:00:00\"]},{\"date\":\"2025-04-03\",\"walkingHours\":[\"15:00:00\",\"21:00:00\"]},{\"date\":\"2025-04-04\",\"walkingHours\":[\"15:00:00\",\"21:00:00\"]}],\"dogWalkerId\":2}";

}
