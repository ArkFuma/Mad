package com.ace.zero.screens;

import com.ace.zero.GameObject;
import com.ace.zero.GameStateManager;
import com.ace.zero.MainGame;
import com.ace.zero.State;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bloom on 26.09.2017.
 * Меню опцій
 */

public class OptionState extends State {

    public OptionState(GameStateManager gameStateManager) {
        super(gameStateManager);
        button = new GameObject[1];
        button[0] = new GameObject("main_menu.png", new Vector2(0, 100));
    }


    @Override
    public void update() {
        super.update();
        if(button[0].isTouched(touchPos)){
            MainGame.gameStateManager.push(new MainMenuState(gameStateManager));
        }
    }
}
