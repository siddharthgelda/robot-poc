package com.robot.poc.dto.impl;

import com.robot.poc.constants.ApplicationContants;
import com.robot.poc.dto.Observer;
import com.robot.poc.dto.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Robot implements Subject {

    private static boolean OVERWEIGHT_INDICATOR = false;
    private static boolean LOW_BATTERY = false;
    String consumptionMsg;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private Double strength;
    private Double load;

    public Robot(Double aStrength, Double aLoad, String aConsumptionMsg) {
        super();
        strength = aStrength;
        load = aLoad;
        consumptionMsg = aConsumptionMsg;

    }

    public Robot() {
        super();
        strength = 100.0;
        load = 100.0;
        consumptionMsg = "";

    }

    /**
     * Calculate the Capacity & efficiency of the robot based on the activity performed by it.
     */
    public void consumption() {
        try {
            double unitPercentileStrength = strength / ApplicationContants.MAX_ROBOT_STRENGTH;
            observers.stream().forEach(ob -> {
                // System.out.println("Distance " + ob.getDistanceInput());
                // System.out.println("Weight " + ob.getWeightInput());

                double distance = ob.getDistanceInput();
                double weight = ob.getWeightInput();

                if (null != ob.getDistanceInput() && null != ob.getWeightInput()) {
                    if (distance != 0.0) {
                        double result = unitPercentileStrength * distance;
                        strength = strength - result;
                        if (strength <= ApplicationContants.INDICATING_LOW_BATTERY) {
                            LOW_BATTERY = true;
                        }
                    }
                    if (weight > ApplicationContants.MAX_ROBOT_LOAD_THRESHOLD && !LOW_BATTERY) {
                        OVERWEIGHT_INDICATOR = true;

                    } else {
                        strength = strength - (ApplicationContants.KILOGRAM_CARRIED_BATTERY_CONSUMED * weight);
                        if (strength <= ApplicationContants.INDICATING_LOW_BATTERY) {
                            LOW_BATTERY = true;
                        }
                    }

                    notifyObservers(ob, strength.toString(), LOW_BATTERY, OVERWEIGHT_INDICATOR);

                    // System.out.println("Result" + strength);

                }

                // ob.getMessages(this.consumptionMsg);
               /* strength = 100.0;
                LOW_BATTERY = false;
                OVERWEIGHT_INDICATOR = false;*/
            });

        } catch (Exception e) {
            System.out.println(ApplicationContants.INPUT_NAN_KEY);
        }

    }

    /**
     * @return the observers
     */
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    /**
     * @param aObservers the observers to set
     */
    public void setObservers(ArrayList<Observer> aObservers) {
        observers = aObservers;
    }

    /**
     * @return the strength
     */
    public Double getStrength() {
        return strength;
    }

    /**
     * @param aStrength the strength to set
     */
    public void setStrength(Double aStrength) {
        strength = aStrength;
    }

    /**
     * @return the load
     */
    public Double getLoad() {
        return load;
    }

    /**
     * @param aLoad the load to set
     */
    public void setLoad(Double aLoad) {
        load = aLoad;
    }

    /**
     * @return the consumptionMsg
     */
    public String getConsumptionMsg() {
        return consumptionMsg;
    }

    /**
     * @param aConsumptionMsg the consumptionMsg to set
     */
    public void setConsumptionMsg(String aConsumptionMsg) {
        consumptionMsg = aConsumptionMsg;
        // notifyObservers();
    }

    @Override
    public void registerObserver(Observer aObserver) {
        observers.add(aObserver);

    }

    @Override
    public void removeObserver(Observer aObserver) {
        observers.remove(aObserver);

    }

    /**
     * Notifying to all the subscribers and publish the messages
     */
    @Override
    public String notifyObservers(Observer ob, String msg, boolean lb, boolean ow) {

        if (lb) {
            ob.getMessages(ApplicationContants.LOW_BATTERY);
        }
        if (ow) {
            ob.getMessages(ApplicationContants.OVER_WEIGHT);
        }

        if (!lb && !ow) {
            ob.getMessages(msg);
        }

        return msg;

    }
}
