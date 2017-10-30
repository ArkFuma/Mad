package com.ace.zero.screens;

import com.ace.zero.GameObject;
import com.ace.zero.GameStateManager;
import com.ace.zero.MainGame;
import com.ace.zero.State;
import com.badlogic.gdx.math.Vector2;

import static com.ace.zero.State.touchPos;

/**
 * Created by Bloom on 26.09.2017.
 * Досягнення
 */

public class AchievementState extends State {
    public AchievementState(GameStateManager gameStateManager) {
        super(gameStateManager);
        button = new GameObject[2];
        button[0] = new GameObject("main_menu.png", new Vector2(MainGame.WIDTH/2,
                MainGame.HEIGHT/2));
        button[1] = new GameObject("new_game.png", new Vector2(MainGame.WIDTH/2,
                MainGame.HEIGHT/4));
    }

    @Override
    public void update() {
        /**визначаємо на яку кнопку було натиснуто*/
        if(button[0].isTouched(touchPos)){
            /**переходимо в головне меню*/
            MainGame.gameStateManager.push(new MainMenuState(gameStateManager));
        }
        if(button[1].isTouched(touchPos)){
            /**розпочинаємо гру*/
            MainGame.gameStateManager.push(new PlayState(gameStateManager));
        }

    }
}
