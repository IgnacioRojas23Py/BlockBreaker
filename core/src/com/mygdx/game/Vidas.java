package com.mygdx.game;

public class Vidas 
{
    private static Vidas instancia;
    private int cantidad;

    private Vidas() 
    {
        cantidad = 3;
    }

    public static Vidas getInstancia() 
    {
        if (instancia == null) 
        {
            instancia = new Vidas();
        }
        return instancia;
    }

    public int getVidas() 
    {
        return cantidad;
    }

    public void perderVida() 
    {
        if (cantidad > 0) 
        {
            cantidad--;
        }
    }

    public void reiniciar() 
    {
        cantidad = 3;
    }
    
    public void aumentarVida(int puntos)
    {
    	if (puntos % 30 == 0)
    	{
    		cantidad += 1;
    	} // como se aumento la dificultad al darle velocidad, ahora cada 30 puntos se entrega una vida
    }
}
