package me.alien.tic.tac.to;

import com.sun.istack.internal.NotNull;

public class Box {
    private Vector2 start;
    private Vector2 end;

    public Box(Vector2 start, Vector2 end){
        this.start = start;
        this.end = end;
    }

    public Vector2 getEnd() {
        return end;
    }

    public Vector2 getStart() {
        return start;
    }

    public void setEnd(Vector2 end) {
        this.end = end;
    }

    public void setStart(Vector2 start) {
        this.start = start;
    }

    public Boolean contains(Vector2 pos){
        if(pos == null) return false;

        return(((start.getX() < pos.getX()) && (end.getX() > pos.getX())) && ((start.getY() < pos.getY()) && (end.getY() > pos.getY())));
    }

    public int getW(){
        return end.getX() - start.getX();
    }

    public int getH(){
        return end.getY() - start.getY();
    }
}
