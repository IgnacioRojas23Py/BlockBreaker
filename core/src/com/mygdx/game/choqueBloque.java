/*package com.mygdx.game;

public class choqueBloque implements chocar  //implementa la interfaz chocar
{
    @Override
    public void responder(PingBall pelota, boolean huboChoque) 
    {
        // PingBall resuelve la dirección y la posición del rebote.
    	//Hacer que coordine un efecto especifico ante cierta colision
    }
}*/


package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class choqueBloque implements chocar {

    @Override
    public void responder(PingBall pelota, boolean huboChoque) {

        if (huboChoque) {
            pelota.setColor(Color.CYAN);
        }
        else {
            pelota.setColor(Color.WHITE);
        }
    }
}
