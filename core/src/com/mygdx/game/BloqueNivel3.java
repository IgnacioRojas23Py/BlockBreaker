package com.mygdx.game;

public class BloqueNivel3 extends TipoBloque {

    public BloqueNivel3() {
        super(3);
    }

    @Override
    protected void actualizarEstadoVisual() {

        if (getImpactosRecibidos() == 1) {
            setEstadoVisual(1);
        }
        else if (getImpactosRecibidos() == 2) {
            setEstadoVisual(2);
        }
    }
}
