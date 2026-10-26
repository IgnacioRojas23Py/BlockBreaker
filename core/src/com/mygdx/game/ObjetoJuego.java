package com.mygdx.game;

public abstract class ObjetoJuego      
{   
    private int x,y,width,height;

    public ObjetoJuego(int x, int y, int width, int height) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public void setX(int newX) {x = newX;}
}
