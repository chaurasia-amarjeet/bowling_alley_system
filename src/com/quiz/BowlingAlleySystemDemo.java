package com.quiz;

import java.util.UUID;

public class BowlingAlleySystemDemo {

    public static void main(String[] args) {
        BowlingAlleySystem bowlingAlleySystem = new BowlingAlleySystem(3);
        bowlingAlleySystem.submitRequest(UUID.randomUUID().toString());
        bowlingAlleySystem.submitRequest(UUID.randomUUID().toString());
        bowlingAlleySystem.submitRequest(UUID.randomUUID().toString());
        bowlingAlleySystem.submitRequest(UUID.randomUUID().toString());
        bowlingAlleySystem.submitRequest(UUID.randomUUID().toString());

//        Thread bowlingThread = new Thread(bowlingAlleySystem::start);
//        bowlingThread.start();
        bowlingAlleySystem.start();

        //bowlingThread.join();
    }
}
