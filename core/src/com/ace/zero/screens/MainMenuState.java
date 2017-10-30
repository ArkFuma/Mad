package com.ace.zero.screens;

import com.ace.zero.GameObject;
import com.ace.zero.GameStateManager;
import com.ace.zero.MainGame;
import com.ace.zero.State;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bloom on 26.09.2017.
 * Головне меню
 */

public class MainMenuState extends State {

    public MainMenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        /** встановлюємо видове вікно та вирівнюємо по центру*/
        //camera.setToOrtho(false, MainGameClass.WIDTH, MainGameClass.HEIGHT);
        button = new GameObject[3];
        button[0] = new GameObject("start.png", new Vector2(10, 10), new Vector2(), new Vector2(0.5f, 0.5f), 0f);
        button[1] = new GameObject("options.png", new Vector2(50, 50), new Vector2(), new Vector2(0.5f, 0.5f), 0f);
        button[2] = new GameObject("score.png", new Vector2(100, 100), new Vector2(), new Vector2(0.5f, 0.5f), 0f);

    }

    @Override
    public void update() {
        handleInput();
        /**визначаємо на яку кнопку було натиснуто*/
        if(button[0].isTouched(touchPos)){
            /**розпочинаємо гру*/
            MainGame.gameStateManager.push(new PlayState(gameStateManager));
        }
        if(button[1].isTouched(touchPos)) {
            /**заходимо в меню опцій*/
            MainGame.gameStateManager.push(new OptionState(gameStateManager));
        }
        if(button[2].isTouched(touchPos)){
            /**заходимо в меню досягнень*/
            MainGame.gameStateManager.push(new AchievementState(gameStateManager));
        }
    }

}
