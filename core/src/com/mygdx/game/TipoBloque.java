package com.mygdx.game;

public abstract class TipoBloque {

    private int golpesRestantes;
    private int impactosRecibidos;
    private int estadoVisual;

    public TipoBloque(int golpesRestantes) {
        this.golpesRestantes = golpesRestantes;
        this.impactosRecibidos = 0;
        this.estadoVisual = 0;
    }

    // Método plantilla: establece el orden del algoritmo.
    public final boolean golpe() {

        if (estaDestruido()) {
            return true;
        }

        registrarImpacto();

        reducirResistencia();

        actualizarEstadoVisual();

        return estaDestruido();
    }

    // Paso común: registrar el impacto.
    private void registrarImpacto() {
        impactosRecibidos++;
    }

    // Paso común: reducir la resistencia.
    private void reducirResistencia() {
        golpesRestantes--;
    }

    // Paso que cada subclase debe personalizar.
    protected abstract void actualizarEstadoVisual();

    // Comprobar si el bloque fue destruido.
    protected boolean estaDestruido() {
        return golpesRestantes <= 0;
    }

    protected int getImpactosRecibidos() {
        return impactosRecibidos;
    }

    protected void setEstadoVisual(int estado) {
        estadoVisual = estado;
    }

    public int getEstadoVisual() {
        return estadoVisual;
    }
}
