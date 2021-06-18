package com.wengnerits.jfr.agent.demo;

import java.util.Random;

/**
 * @author Miroslav Wengner (@miragemiko, @mirage22)
 */
public class VehicleFactory implements Runnable {

    private static final String[] VEHICLE_PRODUCERS = new String[]{"VW", "BMW", "Skoda", "Dacia"};
    private static final int ACTION_DURATION_MAX_MS = 400;
    private static final int ACTION_VEHICLES_MAX = 5;
    private static final Random RANDOM = new Random();
    private static int id_counter = 0;

    private enum State {
        PREPARE, PRODUCING, DELIVERING
    }

    private final int id = id_counter++;
    private final String name;
    private State state;
    private int counter;

    public VehicleFactory() {
        this.name = VEHICLE_PRODUCERS[RANDOM.nextInt(VEHICLE_PRODUCERS.length)] + "#" + id;
        this.state = State.PREPARE;
        this.counter = 0;
    }

    @Override
    public void run() {
        try {
            while (counter < ACTION_VEHICLES_MAX) {
                switch (state) {
                    case PREPARE:
                        prepareParts("engine");
                        break;
                    case PRODUCING:
                        produceVehicle("vehicle");
                        break;
                    case DELIVERING:
                        deliverVehicle();
                        break;
                    default:
                        throw new IllegalArgumentException("not allowed state:" + state);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void reset() {
        id_counter = 0;
    }

    void prepareParts(String p) throws InterruptedException {
        System.out.println("factory: " + name + ", vehicle: "+ counter +", preparing parts: " + p);
        makeWork();
        state = State.PRODUCING;
    }

    void produceVehicle(String v) throws InterruptedException {
        System.out.println("factory:" + name + ", vehicle: "+ counter +", producing vehicle: " + v);
        makeWork();
        state = State.DELIVERING;
    }

    void deliverVehicle() throws InterruptedException {
        System.out.println("factory: " + name + ", vehicle: "+ counter +", delivering vehicle: ");
        makeWork();
        state = State.DELIVERING;
        counter++;
    }

    private void makeWork() throws InterruptedException {
        Thread.sleep((long) (Math.random() * ACTION_DURATION_MAX_MS));
    }

    @Override
    public String toString() {
        return "VehicleFactory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
