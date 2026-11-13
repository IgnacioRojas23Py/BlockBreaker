package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class choquePaddle implements chocar //implementa la interfaz chocar
{
	@Override
	public void responder(PingBall pelota, boolean huboChoque) 
	{
		if (huboChoque) 
		{
			pelota.rebotarEnPaddle();
			pelota.setColor(Color.LIME);
		} 
		else 
		{
			pelota.setColor(Color.WHITE);
		}
	}

}
