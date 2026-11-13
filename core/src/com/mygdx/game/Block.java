/*un bloque del juego
package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;
import com.badlogic.gdx.graphics.Color;

public class Block extends ObjetoJuego implements Dibuja //Hereda la clase abstracta
{
    private Color cc;
    private boolean destroyed;
    private TipoBloque tipo;

    public Block(int x, int y, int width, int height) 
    {
        this(x, y, width, height, new BloqueNivel1());
    }

    public Block(int x, int y, int width, int height, TipoBloque tipo) 
    {
        super(x,y,width,height);
        destroyed = false;	
        this.tipo = tipo;
        Random r = new Random(x+y);
        cc = new Color(0.1f+r.nextFloat(), r.nextFloat(), r.nextFloat(), 10); 
    }

    // devuelve una copia para proteger el color que guarda el bloque
    public Color getColor() {return new Color(cc);}
    
    //guarda una copia para que el color recibido no pueda cambiar el del bloque despues
    public void setColor(Color newCC) {cc = new Color(newCC);}
    public boolean getDestroyed() {return destroyed;}
    
    // el bloque puede destruirse pero no volver a activarse con un setter 
    public void destruir() {destroyed = true;}

    public void recibirGolpe() 
    {
        if (tipo.golpe()) 
        {
            destruir();
        }
    }
    

    @Override
    public void draw(ShapeRenderer shape)
    {
        shape.setColor(cc);
        shape.rect(getX(), getY(), getWidth(), getHeight());
    }
}*/

package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;
import com.badlogic.gdx.graphics.Color;

public class Block extends ObjetoJuego implements Dibuja {

    private Color cc;
    private Color colorOriginal;

    private boolean destroyed;
    private TipoBloque tipo;

    public Block(int x, int y, int width, int height) {
        this(x, y, width, height, new BloqueNivel1());
    }

    public Block(int x, int y, int width, int height, TipoBloque tipo) {

        super(x, y, width, height);

        destroyed = false;
        this.tipo = tipo;

        Random r = new Random(x + y);

        cc = new Color(
            0.1f + r.nextFloat(),
            r.nextFloat(),
            r.nextFloat(),
            1f
        );

        colorOriginal = new Color(cc);
    }

    public Color getColor() {
        return new Color(cc);
    }

    public void setColor(Color newCC) {
        cc = new Color(newCC);
    }

    public boolean getDestroyed() {
        return destroyed;
    }

    public void destruir() {
        destroyed = true;
    }

    public void recibirGolpe() {

        if (destroyed) {
            return;
        }

        if (tipo.golpe()) {
            destruir();
        }
        else {
            actualizarColor();
        }
    }

    // Representación visual según el estado del tipo de bloque.
    private void actualizarColor() {

        int estado = tipo.getEstadoVisual();

        switch (estado) {

            case 1:
                cc = Color.YELLOW.cpy();
                break;

            case 2:
                cc = Color.ORANGE.cpy();
                break;

            default:
                cc = new Color(colorOriginal);
                break;
        }
    }

    @Override
    public void draw(ShapeRenderer shape) {

        shape.setColor(cc);

        shape.rect(
            getX(),
            getY(),
            getWidth(),
            getHeight()
        );
    }
}
