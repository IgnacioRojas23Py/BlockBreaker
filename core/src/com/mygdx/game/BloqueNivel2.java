package com.mygdx.game;

public class BloqueNivel2 extends TipoBloque {

    public BloqueNivel2() {
        super(2);
    }

    @Override
    protected void actualizarEstadoVisual() {

        if (getImpactosRecibidos() == 1) {
            setEstadoVisual(1);
        }
    }
}
