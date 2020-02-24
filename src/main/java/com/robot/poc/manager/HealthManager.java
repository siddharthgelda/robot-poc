package com.robot.poc.manager;

import com.robot.poc.constants.ApplicationContants;
import com.robot.poc.dto.Observer;
import org.springframework.stereotype.Service;

/*
@author Siddharth Gelda
 */
@Service
public class HealthManager implements Observer {
    private Double distanceInput;
    private Double weightInput;
    private String msgPortlet;

    public HealthManager(Double aDistanceInput, Double aWeightInput) throws Exception {
        super();
        distanceInput = aDistanceInput;
        weightInput = aWeightInput;
    }

    public HealthManager() {
        super();
        distanceInput = 0.0;
        weightInput = 0.0;
    }

    /**
     * @return the distanceInput
     */
    public Double getDistanceInput() {
        return distanceInput;
    }

    /**
     * @param aDistanceInput the distanceInput to set
     */
    public void setDistanceInput(Double aDistanceInput) {
        distanceInput = aDistanceInput;
    }

    /**
     * @return the weightInput
     */
    public Double getWeightInput() {
        return weightInput;
    }

    /**
     * @param aWeightInput the weightInput to set
     */
    public void setWeightInput(Double aWeightInput) {
        weightInput = aWeightInput;
    }

    /**
     * @return the msgPortlet
     */
    public String getMsgPortlet() {
        return msgPortlet;
    }

    /**
     * @param aMsgPortlet the msgPortlet to set
     */
    public void setMsgPortlet(String aMsgPortlet) {
        msgPortlet = aMsgPortlet;
    }

    /**
     * displaying the messages or alerts based on the msg attributes retrieve from the notifier subject
     *
     * @param msg
     */
    public String getMessages(String msg) {
        if (msg.equalsIgnoreCase(ApplicationContants.LOW_BATTERY)) {
            System.out.println(ApplicationContants.LOW_BATTERY_MESSAGE);
            setMsgPortlet(ApplicationContants.LOW_BATTERY_MESSAGE);
        }
        if (msg.equalsIgnoreCase(ApplicationContants.OVER_WEIGHT)) {
            System.out.println(ApplicationContants.OVER_WEIGHT_MESSAGE);
            setMsgPortlet(ApplicationContants.OVER_WEIGHT_MESSAGE);
        }

        if (!msg.equalsIgnoreCase(ApplicationContants.LOW_BATTERY)
                && !msg.equalsIgnoreCase(ApplicationContants.OVER_WEIGHT)) {
            System.out.println(ApplicationContants.DISTANCE_KEY + distanceInput + ApplicationContants.WEIGHT_KEY
                    + weightInput + ApplicationContants.APPLIED_KEY + msg + ApplicationContants.REST_PART_MESSAGE_KEY);
            setMsgPortlet(ApplicationContants.DISTANCE_KEY + distanceInput + ApplicationContants.WEIGHT_KEY
                    + weightInput + ApplicationContants.APPLIED_KEY + msg + ApplicationContants.REST_PART_MESSAGE_KEY);
        }
        return msg;
    }
}
