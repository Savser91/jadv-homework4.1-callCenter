package com.company;

public class ThreadATC extends Thread {
    private final long CALL_TIMEOUT = 1000;
    private final int CALLS_PER_MINUTE = 10;
    private String name;
    private StationATC stationATC;
    private int inputCallCounter = 0;

    public ThreadATC(String name, StationATC stationATC) {
        super(name);
        this.stationATC = stationATC;
    }

    @Override
    public void run() {
        System.out.println("Начало работы " + Thread.currentThread().getName());
        try {
            while (!Thread.currentThread().isInterrupted() &&
                    stationATC.getCallCounter() < stationATC.getCALLS_TO_TAKE()) {
                Thread.sleep(CALL_TIMEOUT);
                for (int i = 0; i < CALLS_PER_MINUTE; i++) {
                    stationATC.getCalls().add(new Call(String.valueOf(inputCallCounter * CALLS_PER_MINUTE + i + 1)));
                }
                inputCallCounter++;
            }
            if (stationATC.getCallCounter() >= stationATC.getCALLS_TO_TAKE()) {
                this.interrupt();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
