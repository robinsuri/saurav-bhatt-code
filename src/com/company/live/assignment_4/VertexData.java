package com.company.live.assignment_4;

public class VertexData {
    private int discoveryTime;
    private int finishTime;

    public VertexData() {
        discoveryTime =-1;
        finishTime =-1;
    }

    public int getDiscoveryTime() {
        return discoveryTime;
    }

    public void setDiscoveryTime(int discoveryTime) {
        this.discoveryTime = discoveryTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
}
