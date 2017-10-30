package com.ace.zero.screens;

import com.ace.zero.GameObject;
import com.ace.zero.GameStateManager;
import com.ace.zero.MainGame;
import com.ace.zero.Map.City;
import com.ace.zero.Objects.Motionless;
import com.ace.zero.State;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bloom on 26.09.2017.
 * Меню гри
 */

public class PlayState extends State {

    Motionless[] motionlesses; // Нерухомі обєкти
    //// Рухомі люди
    //// Рухомі машини

    //// Елемент керування

    public PlayState(GameStateManager gameStateManager)
    {
        super(gameStateManager);
        button = new GameObject[1];
        button[0] = new GameObject("main_menu.png", new Vector2(4000, 3200));

        motionlesses = City.Map0(); // Мапа для нерухомих обєктів
        //Gdx.gl.glClearColor(1, 0.5f, 0, 1);

        camera.position.x = 0; // Початкові координати камери
        camera.position.y = 0;

    }

    @Override
    public void update() {
        super.update();
        if(button[0].isTouched(touchPos)){
            MainGame.gameStateManager.push(new MainMenuState(gameStateManager));
        }
        for (int i =0;i<motionlesses.length; i++)
            if (motionlesses[i]!=null) motionlesses[i].update();
        cameraMove();
    }

    /**
     * Промальовка всіх обєктів
     */
    public void draw() {
        for (int i =0;i<motionlesses.length; i++)
            if (motionlesses[i]!=null) motionlesses[i].draw();

        MainGame.batch.setProjectionMatrix(camera.projection);
        MainGame.bitmapFont.draw(MainGame.batch, ""+camera.position.x + ";"+ camera.position.y + "\n" + touchPos.toString(), -200,200);
        MainGame.batch.setProjectionMatrix(camera.combined);

        /*shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,0,1);
        shapeRenderer.triangle(10,10,40,10, 30, 20);
        shapeRenderer.end();*/

    }

    /**
     * Рухи камери замінити на такі які будуть слідкувати за персонажами
     */
    public void cameraMove() {
        if (Gdx.input.isButtonPressed(2))
        {
            int multi = 4;

            if (touchPos.x > camera.position.x/* * camera.zoom*/)
                camera.position.x += multi;
            else
                camera.position.x -= multi;

            if (touchPos.y > camera.position.x/* * camera.zoom*/)
                camera.position.y += 0.866 * multi;
            else
                camera.position.y -= 0.866 * multi;
        }
        if (Gdx.input.isButtonPressed(0))
        {
            int multi = 5;

            if (touchPos.y > camera.position.x)
                camera.zoom +=  (multi * 0.01f);
            else
                camera.zoom -= (multi * 0.01);
        }
    }
}
