package com.quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BowlingAlleySystem {
    LinkedList<Lane> lanes;
    LinkedList<String> requests;
    ExecutorService executorService;

    public BowlingAlleySystem(int laneCount) {
        lanes = new LinkedList<>();
        addLanes(laneCount);
        requests = new LinkedList<>();
        executorService = Executors.newFixedThreadPool(laneCount);
    }

    public void start() {
        try{
        //int count  = 0;
            while(true){
                if(lanes.peek().getStatus() != LaneStatus.AVAILABLE){
                    Thread.sleep(1000);
//                    count++;
//                    if(count == 5){
//                        System.out.println("Stopping system...");
//                        break;
//                    }
                    continue;
                }

                if(requests.isEmpty()){
                    break;
                }

                Lane lane = lanes.pollFirst();
                lane.setStatus(LaneStatus.BUSY);
                lanes.addLast(lane);
                Game game  = setupGame(requests.pollFirst(), lane);
                System.out.println("Game is setup now...starting game!");

                executorService.submit(game::startGame);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

    public void submitRequest(String requestId){
        requests.addLast(requestId);
    }

    private void addLanes(int laneCount){
        for(int i = 1; i <= laneCount; i++){
            System.out.println("Adding lane: " + i);
            lanes.add(new Lane(i));
        }
    }

    private Game setupGame(String requestId, Lane lane){
        System.out.println("Adding Players...");
        List<Player> players = new LinkedList<>();
        players.add(new Player("Deepak Singh"));
        players.add(new Player("Amarjeet"));
        players.add(new Player("Dinesh"));

        return new Game(lane, players, requestId);
    }
}
