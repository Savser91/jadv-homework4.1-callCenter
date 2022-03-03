package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        StationATC stationATC = new StationATC();
        stationATC.startWorking();

        System.out.println("Конец работы программы");
    }
}
