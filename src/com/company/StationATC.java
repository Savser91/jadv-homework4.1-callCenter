package com.company;

import java.util.*;
import java.util.concurrent.*;

public class StationATC {
    ThreadATC threadATC = new ThreadATC("АТС", this);
    List<Specialist> specialists = new ArrayList<Specialist>();
    private ConcurrentLinkedQueue<Call> calls = new ConcurrentLinkedQueue<Call>();
    private final int NUMBER_OF_SPECIALISTS = 10;
    private int callCounter = 0;
    private final int CALLS_TO_TAKE = 50;

    public StationATC() {

    }

    public void startWorking() throws InterruptedException {
        System.out.println("Работа началась");
        threadATC.start();

        for (int i = 0; i < NUMBER_OF_SPECIALISTS; i++) {
            Specialist specialist = new Specialist("Специалист № " + (i + 1), this);
            specialists.add(specialist);
            specialist.start();
        }

        while (true) {
            if (!stillWorking()) {
                stopWorking();
                break;
            }
        }
    }

    public void stopWorking() {
        for (int i = 0; i < specialists.size(); i++) {
            specialists.get(i).interrupt();
        }
        System.out.println("Работа завершена");
    }

    private boolean stillWorking() {
        return threadATC.isAlive();
    }

    public void setCallCounter() {
        callCounter++;
    }

    public ConcurrentLinkedQueue<Call> getCalls() {
        return calls;
    }

    public int getCallCounter() {
        return callCounter;
    }

    public int getCALLS_TO_TAKE() {
        return CALLS_TO_TAKE;
    }
}
