package com.mygdx.game;

public abstract class TipoBloque 
{
    private int golpesRestantes;

    public TipoBloque(int golpesRestantes) 
    {
        this.golpesRestantes = golpesRestantes;
    }

    // Método plantilla
    public final boolean golpe() 
    {
        reducirResistencia();
        return estaDestruido();
    }

    protected void reducirResistencia() 
    {
        golpesRestantes--;
    }

    protected boolean estaDestruido() 
    {
        return golpesRestantes <= 0;
    }
}
