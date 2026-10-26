package com.mygdx.game;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall implements Dibuja   
{
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private Color color = Color.WHITE;
    private boolean estaQuieto;

    private final Map<ObjetoJuego, Boolean> colisionesActivas = Collections.synchronizedMap(new WeakHashMap<ObjetoJuego, Boolean>());

    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) 
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.estaQuieto = iniciaQuieto;
    }

    public boolean estaQuieto() 
    {
        return estaQuieto;
    }

    public void setEstaQuieto(boolean bb) 
    {
        estaQuieto = bb;
    }

    public void setXY(int x, int y) 
    {
        this.x = x;
        this.y = y;
        colisionesActivas.clear();
    }

    public int getY() 
    {
        return y;
    }

    @Override
    public void draw(ShapeRenderer shape) 
    {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void update() 
    {
        if (estaQuieto) return;

        // Subdividir el movimiento para reducir atravesamientos.
        int pasos = Math.max(1, (int) Math.ceil(Math.max(Math.abs(xSpeed), Math.abs(ySpeed)) / 2.0));

        float dx = (float) xSpeed / pasos;
        float dy = (float) ySpeed / pasos;

        for (int i = 0; i < pasos; i++) 
        {
            x += Math.round(dx);
            y += Math.round(dy);

            if (x - size < 0) 
            {
                x = size;
                if (xSpeed < 0) xSpeed = -xSpeed;
            } 
            else if (x + size > Gdx.graphics.getWidth()) 
            {
                x = Gdx.graphics.getWidth() - size;
                if (xSpeed > 0) xSpeed = -xSpeed;
            }

            if (y + size > Gdx.graphics.getHeight()) 
            {
                y = Gdx.graphics.getHeight() - size;
                if (ySpeed > 0) ySpeed = -ySpeed;
            }
        }
    }



public boolean checkCollision(ObjetoJuego objeto, chocar estrategia) 
{
    boolean huboChoque = collidesWith(objeto);
    boolean contactoAnterior = colisionesActivas.containsKey(objeto);

    if (!huboChoque) 
    {
        colisionesActivas.remove(objeto);
        estrategia.responder(this, false);
        return false;
    }

    if (contactoAnterior) 
    {
        return false;
    }

    colisionesActivas.put(objeto, true);

    resolverColision(objeto);
    estrategia.responder(this, true);

    return true;
}



    private boolean collidesWith(ObjetoJuego objeto) 
    {
        return x + size >= objeto.getX() && x - size <= objeto.getX() + objeto.getWidth() && y + size >= objeto.getY() && y - size <= objeto.getY() + objeto.getHeight();
    }

    private void resolverColision(ObjetoJuego objeto) 
    {
        int izquierda = objeto.getX();
        int derecha = izquierda + objeto.getWidth();
        int abajo = objeto.getY();
        int arriba = abajo + objeto.getHeight();

        int solapamientoIzq = x + size - izquierda;
        int solapamientoDer = derecha - (x - size);
        int solapamientoAbajo = y + size - abajo;
        int solapamientoArriba = arriba - (y - size);

        int solapamientoX = Math.min(solapamientoIzq, solapamientoDer);
        int solapamientoY = Math.min(solapamientoAbajo, solapamientoArriba);

        if (solapamientoX < solapamientoY) 
        {
            if (x < izquierda + objeto.getWidth() / 2) 
            {
                x = izquierda - size;
            } 
            else 
            {
                x = derecha + size;
            }

            xSpeed = -xSpeed;
        } 
        else 
        {
            if (y >= abajo + objeto.getHeight() / 2) 
            {
                y = arriba + size;
            } 
            else 
            {
                y = abajo - size;
            }

            ySpeed = -ySpeed;
        }
    }

    void invertirVelocidadY() 
    {
        ySpeed = -ySpeed;
    }

    void setColor(Color color) 
    {
        this.color = new Color(color);
    }

    public void aumentarVelocidadPorNivel(int nivel) 
    {
        ySpeed += nivel - 1;
        xSpeed += nivel - 1;
    }
    

    void rebotarEnPaddle() 
    {
    	if (ySpeed < 0) 
    	{
    		ySpeed = Math.abs(ySpeed);
    	}
    }

}
