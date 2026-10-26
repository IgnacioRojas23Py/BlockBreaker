package com.mygdx.game;

public class BlockDirector 
{
    private Builder builder;

    public BlockDirector(Builder builder) 
    {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) 
    {
        this.builder = builder;
    }

    public Block construirBloque(int x, int y, int width, int height, TipoBloque tipo) 
    {
        builder.reset();
        builder.construirPosicion(x, y);
        builder.construirDimensiones(width, height);
        builder.construirTipo(tipo);

        return builder.getResult();
    }
}
