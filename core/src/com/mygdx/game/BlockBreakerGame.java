package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BlockBreakerGame extends ApplicationAdapter 
{

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer shape;

    private PingBall ball;
    private Paddle pad;
    private ArrayList<Block> blocks = new ArrayList<>();

    private Vidas vidas;
    private int puntaje;
    private int nivel;

    private chocar colisionBloque = new choqueBloque();
    private chocar colisionPaleta = new choquePaddle();

    private enum EstadoJuego 
    {
        MENU, JUGANDO, DERROTA, VICTORIA
    }

    private EstadoJuego estado = EstadoJuego.MENU;
    private static final int NIVEL_MAXIMO = 3;
    
    private Builder blockBuilder = new BlockBuilder();
    private BlockDirector blockDirector = new BlockDirector(blockBuilder);
    

    @Override
    public void create() 
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);

        shape = new ShapeRenderer();

        pad = new Paddle(350, 40, 100, 10);
        vidas = Vidas.getInstancia();

        reiniciarPartida();
        estado = EstadoJuego.MENU;
    }

    private void crearPelota() 
    {
        ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, 10, 5, 7, true);
    }

    public void crearBloques(int filas) 
    {
        blocks.clear();

        int blockWidth = 70;
        int blockHeight = 26;
        int y = Gdx.graphics.getHeight();

        for (int cont = 0; cont < filas; cont++) 
        {
            y -= blockHeight + 10;
            for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) 
            {

            	TipoBloque tipo;

            	if (nivel == 1) 
            	{
            	    tipo = new BloqueNivel1();
            	} 
            	else if (nivel == 2) 
            	{
            	    tipo = new BloqueNivel2();
            	} 
            	else 
            	{
            	    tipo = new BloqueNivel3();
            	}

                Block bloque = blockDirector.construirBloque(x, y, blockWidth, blockHeight, tipo);
                blocks.add(bloque);
            }
        }
    }

    private void reiniciarPartida() 
    {
        puntaje = 0;
        nivel = 1;

        vidas.reiniciar();
        crearBloques(2 + nivel);
        crearPelota();
    }

    //la partida en tiempo de ejecucion
    private void actualizarPartida() 
    {
        // Mantener la pelota sobre la paleta hasta lanzarla.
        if (ball.estaQuieto()) 
        {
            ball.setXY(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11);

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) 
            {
                ball.setEstaQuieto(false);
            }
        } 
        else 
        {
            ball.update();
        }

        // La pelota cayó: perder una vida y preparar otro lanzamiento.
        if (ball.getY() < 0) 
        {
            vidas.perderVida();
            crearPelota();

            if (vidas.getVidas() <= 0) 
            {
            	estado = EstadoJuego.DERROTA;
            	return;
            }
        }

        // Comprobar primero la paleta.
        ball.checkCollision(pad, colisionPaleta);

        // Procesar como máximo un bloque por actualización.
        for (Block b : blocks) 
        {
            if (!b.getDestroyed() && ball.checkCollision(b, colisionBloque)) 
            {
                b.recibirGolpe();
                break;
            }
        }

        // Retirar los bloques destruidos y actualizar la puntuación.
        Iterator<Block> iterador = blocks.iterator();

        while (iterador.hasNext()) 
        {
            Block b = iterador.next();

            if (b.getDestroyed()) 
            {
                iterador.remove();
                puntaje++;
                vidas.aumentarVida(puntaje);
            }
        }

        // Avanzar de nivel solo cuando se hayan eliminado los bloques.
        if (blocks.isEmpty()) 
        {
            if (nivel >= NIVEL_MAXIMO) 
            {
                estado = EstadoJuego.VICTORIA;
                return;
            }

            nivel++;
            crearBloques(2 + nivel);
            crearPelota();
            ball.aumentarVelocidadPorNivel(nivel);
        }
    }

    private void dibujarPartida() 
    {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        pad.draw(shape);

        for (Block b : blocks) 
        {
            b.draw(shape);
        }

        ball.draw(shape);
        shape.end();
        dibujaTextos();
    }

    private void dibujaTextos() 
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        font.draw(batch, "Puntos: " + puntaje, 10, 465);
        font.draw(batch, "Vidas: " + vidas.getVidas(), 620, 465);
        font.draw(batch, "Nivel: " + nivel, 350, 465);

        batch.end();
    }

    private void dibujarMensaje(String titulo, String linea1, String linea2) 
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        font.draw(batch, titulo, 230, 300);
        font.draw(batch, linea1, 140, 230);
        font.draw(batch, linea2, 190, 170);

        batch.end();
    }

    private void dibujarMenu() 
    {
        dibujarMensaje("BLOCK BREAKER", "Destruye todos los bloques", "Pulsa ESPACIO para comenzar");
    }

    private void dibujarDerrota() 
    {
        dibujarMensaje("DERROTA", "Te has quedado sin vidas", "Pulsa R para volver a jugar");
    }

    private void dibujarVictoria() 
    {
        dibujarMensaje("VICTORIA", "Has completado los 3 niveles", "Pulsa R para volver a jugar");
    }

    //lo que ocurre en cada fotograma
    @Override
    public void render() 
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (estado) 
        {
            case MENU:
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) 
                {
                    estado = EstadoJuego.JUGANDO;
                }
                dibujarMenu();
                break;

            case JUGANDO:
                actualizarPartida();
                if (estado == EstadoJuego.JUGANDO) 
                {
                    dibujarPartida();
                } 
                else if (estado == EstadoJuego.DERROTA) 
                {
                    dibujarDerrota();
                } 
                else if (estado == EstadoJuego.VICTORIA) 
                {
                    dibujarVictoria();
                }
                break;

            case DERROTA:
                dibujarDerrota();

                if (Gdx.input.isKeyJustPressed(Input.Keys.R)) 
                {
                    reiniciarPartida();
                    estado = EstadoJuego.JUGANDO;
                }
                break;

            case VICTORIA:
                dibujarVictoria();

                if (Gdx.input.isKeyJustPressed(Input.Keys.R)) 
                {
                    reiniciarPartida();
                    estado = EstadoJuego.JUGANDO;
                }
                break;
        }
    }

    //liberar recursos graficos
    @Override
    public void dispose() 
    {
        batch.dispose();
        font.dispose();
        shape.dispose();
    }
}

