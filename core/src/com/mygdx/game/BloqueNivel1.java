/*package com.mygdx.game;

public class BloqueNivel1 extends TipoBloque 
{
    public BloqueNivel1() 
    {
        super(1);
    }
}*/

package com.mygdx.game;

public class BloqueNivel1 extends TipoBloque {

    public BloqueNivel1() {
        super(1);
    }

    @Override
    protected void actualizarEstadoVisual() {
        // El bloque mantiene su apariencia original.
        setEstadoVisual(0);
    }
}
