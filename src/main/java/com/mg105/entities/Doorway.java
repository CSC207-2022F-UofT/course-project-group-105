package com.mg105.entities;

public class Doorway {
    private int position;
    private int NextRoomPosition;

    public Doorway(int pos, int NextPos)
    {
        this.position = pos;
        this.NextRoomPosition = NextPos;
    }

    public int getPosition()
    {
        return this.position;
    }

    public int getNextRoomPosition()
    {
        return this.NextRoomPosition;
    }

}
