package com.robot.poc;

import com.robot.poc.dto.impl.Robot;
import com.robot.poc.manager.HealthManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
@author Siddharth Gelda
 */
@SpringBootTest
class PocApplicationTests {
    @Autowired
    Robot robot;
    // To test the case when Robot walks for 3.5 KM and carrying 0 KG weight
    HealthManager StrengthHealthManager = null;
    // To test the case when Robot walks for 2 KM and carrying 3 KG weight
    HealthManager EfficiencyHealthManager = null;
    // To test the case when Robot attempt to carrying 12 KG weight
    HealthManager loadHealthManager = null;

    //barcode fail case
    HealthManager barCodeFailerHealthManager = null;

    @Test
    void robotTest() {

        try {
            StrengthHealthManager = new HealthManager(3.5, 0.0, "1234");
            EfficiencyHealthManager = new HealthManager(2.0, 3.0);
            loadHealthManager = new HealthManager(0.0, 12.0);
            barCodeFailerHealthManager = new HealthManager(0.0, 0.0, "djkfdsfjd");
        } catch (Exception e) {
            System.out.println("Provided Inputs are not correct");
        }
        robot.registerObserver(StrengthHealthManager);
        robot.registerObserver(EfficiencyHealthManager);
        robot.registerObserver(loadHealthManager);
        robot.registerObserver(barCodeFailerHealthManager);
        // kickoff to notifying messages on console for all the above mention 3 cases.
        // Per requirement increase the no of cases by simply registering the Observer.
        robot.consumption();
    }

}
