package com.ace.zero.screens;

import com.ace.zero.GameObject;
import com.ace.zero.GameStateManager;
import com.ace.zero.State;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bloom on 26.09.2017.
 * Перехідне меню
 */

public class SubState extends State {

    public SubState(GameStateManager gameStateManager) {
        super(gameStateManager);
        button = new GameObject[1];
        button[0] = new GameObject("main_menu.png", new Vector2(0, 100));
    }


    @Override
    public void update() {
        super.update();
        //if(button[0].isTouched(touchPos)){
        //    MainGameClass.gameStateManager.push(new MainMenuState(gameStateManager));
        //}
    }
}
