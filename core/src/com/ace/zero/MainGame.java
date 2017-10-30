package com.ace.zero;

import com.ace.zero.screens.PlayState;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Main class that starts all other
 * entering point
 * */
public class MainGame extends Game {

	/** ширина ігрової області*/
	public static final int WIDTH = 960;//540;
	/** висота ігрової області*/
	public static final int HEIGHT = 540;//960;

	public static boolean song; // звуки в грі
	public static boolean music; // музика в грі
	public static boolean showAllInfo; // Показати інформацію про всі обєкти зразу, якщо true - то показує

	public static int counter; // лічильник циклів від початку запуска
	public static float speedTime; // ігрова швидкість
	public static float gravity; // гравітація в грі
	public static float FPS; // ФПС

	/**
	 * Керувач промальовкою
	 */
	public static LayerRuler layerRuler;

	/**
	 * Екземпляр тексту для промальовки
	 */
	public static BitmapFont bitmapFont;

	// Time
	// Language

	/** надає текстуру та координати для малювання фігур*/
	public static SpriteBatch batch;

	/**
	 * Штука яка малює примімітіви
	 */
	public static ShapeRenderer shapeRenderer;

	/**
	 * Регулює переходи між менюшками
	 */
	public static GameStateManager gameStateManager;


	@Override
	public void create () {


		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		gameStateManager = new GameStateManager();

		gameStateManager.push(new PlayState(gameStateManager)); // встановлюємо перший екран

		song = false; // Звуків немає
		music = false; // Музики немає
		showAllInfo = false; // Показати всю інформацію про обєкти = не показувати

		counter = 0; // Лічильник на нулі
		speedTime = 1; // Ігрова швидкість по дефолту - 1
		gravity = 10; // Гравітація про дефолту - 10
		FPS = 0; // ФПС - не визначений

		layerRuler = new LayerRuler(batch, shapeRenderer);

		bitmapFont = new BitmapFont();
		bitmapFont.setColor(0, 0.7f, 0, 1); // Встановлюємо колір тексту

		Gdx.gl.glClearColor(0, 1, 1, 1); // Після кожного стирання - СиньоЗелений колір
	}

	@Override
	public void render () {
		/**пов'язано із наслідуванням від Game*/
		super.render();
		/**очищає буфер*/
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/** додаємо лічильник */
		counter++;
		/**оновлюємо перший в стан в стеці*/
		gameStateManager.update();
		/**малюємо перший стан в стеці*/
		gameStateManager.draw();
	}

	@Override
	public void dispose () {
		batch.dispose();

		/**для виводу в консоль*/
		System.out.println("MainGameClass disposed");
	}
}
