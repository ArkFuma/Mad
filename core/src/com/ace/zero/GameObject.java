package com.ace.zero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by NoOne on 06.10.2017.
 * Start class for all objects
 */

/**
 * Базовий клас для всіх обєктів
 */
public class GameObject
{
    protected Texture texture; // Текстура
    protected Vector2 position; // Позиція
    protected Vector2 origin; // точка кручення
    protected Vector2 wh; // висота ширина на яку картинка зріжеться
    protected Vector2 scale; // маштаб
    protected Vector2 src; // тексель
    protected Vector2 srcWH; // висота ширина текселя
    protected float rotation; // поворот
    protected boolean flipX; // розворот по ОХ
    protected boolean flipY; // розворот по ОY
    public float layer; // слой промальовки
    protected Color color; // колір фільтра

    protected String info; // інформація про обєкти

    public GameObject(Texture texture, Vector2 position, Vector2 origin, Vector2 wh, Vector2 scale,
                      Vector2 src, Vector2 srcWH, float rotation, boolean flipX, boolean flipY, float layer,
                      Color color) {
        this.texture = texture;
        this.position = position;
        this.origin = origin;
        this.wh = wh;
        this.scale = scale;
        this.src = src;
        this.srcWH = srcWH;
        this.rotation = rotation;
        this.flipX = flipX;
        this.flipY = flipY;
        this.layer = layer;
        this.color = color;
        this.info = "";
        this.makeInfo();
    }
    public GameObject(Texture texture, Vector2 position, Vector2 origin, Vector2 scale,
                      float rotation, float layer, Color color) {
        this(texture, position, origin,
                texture==null ? new Vector2(): new Vector2(texture.getWidth(), texture.getHeight()),
                scale, new Vector2(0,0),
                texture==null ? new Vector2(): new Vector2(texture.getWidth(), texture.getHeight()),
                rotation, false, false, layer, color);
    }
    public GameObject(Texture texture, Vector2 position, Vector2 origin, Vector2 scale, float rotation) {
        this(texture, position, origin, new Vector2(texture.getWidth(), texture.getHeight()),
                scale, new Vector2(0,0), new Vector2(texture.getWidth(), texture.getHeight()),
                rotation, false, false, 0, Color.WHITE);
    }
    public GameObject(Texture texture, Vector2 position)    {
        this(texture, position, new Vector2(0, 0), new Vector2(texture.getWidth(), texture.getHeight()),
                new Vector2(1, 1), new Vector2(0,0), new Vector2(texture.getWidth(), texture.getHeight()),
                0, false, false, 0, Color.WHITE);
    }
    public GameObject(Texture texture) {
        this(texture, new Vector2(0, 0), new Vector2(0, 0), new Vector2(texture.getWidth(), texture.getHeight()),
                new Vector2(1, 1), new Vector2(0,0), new Vector2(texture.getWidth(), texture.getHeight()),
                0, false, false, 0, Color.WHITE);
    }
    public GameObject(String texture, Vector2 position, Vector2 origin, Vector2 wh, Vector2 scale,
                      Vector2 src, Vector2 srcWH, float rotation, boolean flipX, boolean flipY, float layer,
                      Color color) {
        this(new Texture(texture), position, origin, wh,
                scale, src, srcWH, rotation, flipX, flipY, layer, color);
    }
    public GameObject(String texture, Vector2 position, Vector2 origin, Vector2 scale,
                      float rotation, float layer, Color color) {
        this(new Texture(texture), position, origin, new Vector2(),
                scale, new Vector2(0,0), new Vector2(),
                rotation, false, false, layer, color);
        wh = new Vector2(this.texture.getWidth(), this.texture.getHeight());
        srcWH = new Vector2(this.texture.getWidth(), this.texture.getHeight());
        this.info = "";
        this.makeInfo();
    }
    public GameObject(String texture, Vector2 position, Vector2 origin, Vector2 scale, float rotation) {
        this(texture, position, origin, scale, rotation, 0, Color.WHITE);
    }
    public GameObject(String texture, Vector2 position) {
        this(texture, position, new Vector2(), new Vector2(1, 1), 0, 0, Color.WHITE);
    }
    public GameObject(String texture) {
        this(texture, new Vector2(), new Vector2(), new Vector2(1, 1), 0, 0, Color.WHITE);
    }

    public void dispose()
    {
        texture.dispose();
    }

    /**
     * Замінити текстуру
     * @param texture
     */
    public void loadTexture(Texture texture) {
        this.texture = texture;
        this.srcWH = new Vector2(texture.getWidth(), texture.getHeight());
        this.wh = new Vector2(texture.getWidth(), texture.getHeight());
    }

    /**
     * Замінити текстуру
     * @param texture
     */
    public void loadTexture(String texture) {
        this.texture = new Texture(texture);
        this.srcWH = new Vector2(this.texture.getWidth(), this.texture.getHeight());
        this.wh = new Vector2(this.texture.getWidth(), this.texture.getHeight());
    }

    /**
     * Замінити текстуру без зміни площі промальовки
     * Тобто, нова картинка буде обрізатися до розмірів попередньої
     * @param texture
     */
    public void loadTextureWhithoutChangingWH(Texture texture) {
        this.texture = texture;
    }

    /**
     * Замінити текстуру без зміни площі промальовки
     * Тобто, нова картинка буде обрізатися до розмірів попередньої
     * @param texture
     */
    public void loadTextureWhithoutChangingWH(String texture) {
        this.texture = new Texture(texture);
    }

    public void update()
    {
        makeInfo();
    }

    public void makeInfo() {
        info = "texture: " + texture.toString() + "\n" +
        "position: " + position.toString() + "\n" +
        "origin: " + origin.toString() + "\n" +
        "wh: " + wh.toString() + "\n" +
        "scale: " + scale.toString() + "\n" +
        "src: " + src.toString() + "\n" +
        "srcWH: " + srcWH.toString() + "\n" +
        "rotation: " + rotation + "\n" +
        "flipX: " + flipX + "\n" +
        "flipY: " + flipY + "\n" +
        "layer: " + layer + "\n" +
        "color: " + color.toString();
    }

    /**
     * для визначення чи був дотик до певної текстури
     * */
    public boolean isTouched(Vector3 touchPos){
        if(Gdx.input.justTouched()){
            if (touchPos.x >= position.x && touchPos.x <= (position.x + texture.getWidth() * scale.x)
                    && touchPos.y >= position.y && touchPos.y <= (position.y + texture.getHeight() * scale.y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Зіткнення з іншими обєктами
     * @param gameObjects інші обєкти з якими може бути зіткнення
     */
    public void collision(GameObject[] gameObjects) {

    }

    /**
     * Додавання в цикл промальовки
     */
    public void draw()
    {
        MainGame.layerRuler.add(this);
    }

    /**
     * Промальовка(метод) яка буде промальовуватися(використовуватися) в спеціальному класі промальовки, стеку промальовки
     * @param spriteBatch
     * @param shapeRenderer
     */
    public void trueDraw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.setColor(color);
        spriteBatch.draw(texture, position.x, position.y, origin.x, origin.y, wh.x, wh.y,
                scale.x, scale.y, rotation, (int)src.x, (int)src.y, (int)srcWH.x, (int)srcWH.y,
                flipX, flipY);
        if (MainGame.showAllInfo)
            MainGame.bitmapFont.draw(MainGame.batch, info, position.x, position.y + texture.getHeight()*scale.y);
    }

    /**
     * Зробити клон цього обєкта
     * @return
     */
    public GameObject clone() {
        return new GameObject(texture, position, origin, wh, scale, src, srcWH,
                rotation, flipX, flipY, layer, color);
    }
}
