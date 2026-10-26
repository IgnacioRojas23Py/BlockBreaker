package com.mygdx.game;

public class BlockBuilder implements Builder 
{
    private int x;
    private int y;
    private int width;
    private int height;
    private TipoBloque tipo;
    private Block bloque;

    @Override
    public void reset() 
    {
        x = 0;
        y = 0;
        width = 70;
        height = 26;
        tipo = new BloqueNivel1();
        bloque = null;
    }

    @Override
    public void construirPosicion(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void construirDimensiones(int width, int height) 
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public void construirTipo(TipoBloque tipo) 
    {
        this.tipo = tipo;
    }

    @Override
    public Block getResult() 
    {
        if (bloque == null) 
        {
            bloque = new Block(x, y, width, height, tipo);
        }
        return bloque;
    }
}
