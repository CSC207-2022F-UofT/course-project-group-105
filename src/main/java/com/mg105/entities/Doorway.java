package com.mg105.entities;

public class Doorway {
    private int position;
    private int nextRoomPosition;

    public Doorway(int pos, int NextPos)
    {
        this.position = pos;
        this.nextRoomPosition = NextPos;
    }

    public int getPosition()
    {
        return this.position;
    }

    public int getNextRoomPosition()
    {
        return this.nextRoomPosition;
    }

}
