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
