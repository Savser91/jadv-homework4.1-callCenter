package com.company;

public class Specialist extends Thread {
    private final long ANSWERING_TIMEOUT = 3000;
    private String name;
    private StationATC stationATC;


    public Specialist (String name, StationATC stationATC) {
        super(name);
        this.stationATC = stationATC;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " начал работу");
        try {
            while (!Thread.currentThread().isInterrupted()) {
                while (stationATC.getCalls().isEmpty());
                Call call = stationATC.getCalls().poll();
                if (call != null) {
                    System.out.println(Thread.currentThread().getName() + " отвечает на " + call.getName());
                    Thread.sleep(ANSWERING_TIMEOUT);
                    System.out.println(Thread.currentThread().getName() + " завершил " + call.getName());
                    stationATC.setCallCounter();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
