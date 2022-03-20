package com.quiz;

public class Lane{
    private final int laneId;
    private LaneStatus status;

    public Lane(int laneId){
        this.laneId = laneId;
        status = LaneStatus.AVAILABLE;
    }

    public int getLaneId() {
        return laneId;
    }

    public LaneStatus getStatus() {
        return status;
    }

    public void setStatus(LaneStatus status) {
        this.status = status;
    }
}
