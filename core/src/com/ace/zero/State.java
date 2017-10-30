package com.ace.zero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Bloom on 26.09.2017.
 * game states
 */

/**
 * Стан меню
 */
public class State {
    /**
     * Локальний лічильник
     */
    int localCounter;

    /**
     * Камера
     */
    protected static OrthographicCamera camera;

    /**
     * Позтиція дотику
     */
    public static Vector3 touchPos;

    /** посилання на менеджер станів гри для керування вікнами*/
    protected GameStateManager gameStateManager;

    /**
     * Набір кнопок
     */
    protected GameObject[] button;

    public State (GameStateManager gameStateManager){
        localCounter = 0;
        camera = new OrthographicCamera();
        /** встановлюємо видове вікно та вирівнюємо по центру*/
        camera.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        touchPos = new Vector3();
        this.gameStateManager = gameStateManager;
        button = null;
    }

    /**
     * обробник користувацького вводу
     * */
    public void handleInput()
    {
        /**Визначаємо координати дотику*/
        if(Gdx.input.isTouched())
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        /**перетворюємо координати дотику в координати світу (відлік від лівого нижнього кута)*/
        camera.unproject(touchPos);

        if(Gdx.input.isButtonPressed(1) == true)
            MainGame.showAllInfo = true;
        else MainGame.showAllInfo = false;
    }
    /**
     * оновлення стану через певні проміжки часу
     * */
    public void update() {
        //float deltaTime = Gdx.graphics.getDeltaTime();
        handleInput(); // Ввод ззовні
        localCounter++; // Плюсування лічильника
        camera.update();

        if (button != null) // обновка всіх клавіш
            for(int i = 0; i < button.length; i++)
                if (button[i] != null)
                    button[i].update();
    }

    /**
     * промальвка стану
     * */
    public void render() {
        if (button != null) // Промальовка клавіш
            for(int i = 0; i<button.length; i++)
                if (button[i] != null)
                    button[i].draw();

        MainGame.batch.begin();
        MainGame.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        MainGame.batch.setProjectionMatrix(camera.combined);
        MainGame.shapeRenderer.setProjectionMatrix(camera.combined);

        MainGame.layerRuler.draw();

        draw();

        MainGame.shapeRenderer.end();
        MainGame.batch.end();
    }

    public void draw() { }
    /**
     * вивільненн ресурсів
     * */
    public void dispose() {
        if (button != null)
            for(int i = 0; i<button.length; i++)
                if (button[i] != null)
                    button[i].dispose();
    }
}
