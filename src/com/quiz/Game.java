package com.quiz;

import java.util.List;

public class Game {
    private static final int TOTAL_SET = 10;
    private static final int SPARE_BONUS_POINT = 5;
    private static final int STRIKE_BONUS_POINT = 10;
    private static final int TOTAL_PINS_PER_SET = 10;

    private List<Player> players;
    private final Lane lane;
    private final String requestId;

    public Game(Lane lane, List<Player> players, String requestId){
        this.lane = lane;
        this.players = players;
        this.requestId = requestId;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public void startGame(){
        System.out.println("Starting new game on lane: " + lane.getLaneId());
        int pinsKnocked1, pinsKnocked2, pinsKnocked3;

        for(int i = 1; i <= TOTAL_SET; i++){
            System.out.println("Set: " + i);
            for(Player player: players){
                pinsKnocked1 = (int)(Math.random() * 10);
                if(pinsKnocked1 == TOTAL_PINS_PER_SET){
                    player.updateScore(TOTAL_PINS_PER_SET + STRIKE_BONUS_POINT);
                    player.setHasExtraRoll(true);
                    continue;
                }

                pinsKnocked2 = (int) (Math.random() * (10-pinsKnocked1));
                if(pinsKnocked1 + pinsKnocked2 == TOTAL_PINS_PER_SET){
                    player.updateScore(TOTAL_PINS_PER_SET + SPARE_BONUS_POINT);
                    player.setHasExtraRoll(true);
                    continue;
                }

                if(i == TOTAL_SET && player.hasExtraRoll()){
                    pinsKnocked3 = (int) (Math.random() * (10 - pinsKnocked1 - pinsKnocked2));
                    player.updateScore(pinsKnocked1 + pinsKnocked2 + pinsKnocked3);
                    continue;
                }

                player.updateScore(pinsKnocked1 + pinsKnocked2);
                System.out.println(player.getName() + "'s total score: " + player.getTotalScore());
            }

            System.out.println();
        }

        Player winner = finalizeWinner(players);

        System.out.println("Winner is: " + winner.getName() + " score: " + winner.getTotalScore());
        System.out.println("Game completed on lane: " + lane.getLaneId());

        lane.setStatus(LaneStatus.AVAILABLE);
    }

    private Player finalizeWinner(List<Player> players){
        int maxScore = -1;
        Player winner = null;

        for(Player player: players){
            if(player.getTotalScore() > maxScore){
                maxScore = player.getTotalScore();
                winner = player;
            }
        }

        return winner;
    }
}
