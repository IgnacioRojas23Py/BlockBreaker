package com.mygdx.game;

public interface Builder 
{
    void reset();
    void construirPosicion(int x, int y);
    void construirDimensiones(int width, int height);
    void construirTipo(TipoBloque tipo);
    Block getResult();
}
