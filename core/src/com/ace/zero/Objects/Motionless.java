package com.ace.zero.Objects;

import com.ace.zero.GameObject;
import com.ace.zero.MainGame;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.awt.Point;

/**
 * Created by Satana on 08.10.2017.
 * Нерухомий обєкт
 */

public class Motionless extends GameObject
{
    Texture textures[]; // Масив текстурок
    int speedAnimation; // Швидкість анімації
    float lifeMax; // Максимальна житуха
    float lifeNow; // Житуха зараз
    float healing; // Швидкість лікування
    Vector2 pictureAA; // Відступ картинки зверху

    protected Color topColor; // Колір зверху
    protected Color leftColor; // Колір зліва
    protected Color rightColor; // Колір зправа

    protected Point pointStart; // Точка початку
    protected Vector3 size; // Розміри обєкта

    public static int newCoordX = 52; // Координати розмірів по Х
    public static int newCoordY = 29; // Координати розмірів по Y
    public static int newCoordZ = 60; // Координати розмірів по Z

    public Motionless(Texture texture, Vector2 position, Vector2 origin, Vector2 scale,
                      float rotation, float layer, Color color,
                      int speedAnimation, float lifeMax, float healing, Vector2 pictureAA,
                      Point pointStart, Vector3 size,
                      Color topColor, Color leftColor, Color rightColor) {
        super(texture, position, origin, scale, rotation, layer, color);
        this.speedAnimation = speedAnimation;
        this.lifeMax = lifeMax;
        this.lifeNow = lifeMax;
        this.healing = healing;
        this.pictureAA = pictureAA;
        this.pointStart = pointStart;
        this.size = size;
        this.topColor = topColor;
        this.leftColor = leftColor;
        this.rightColor = rightColor;
    }

    public Motionless(Texture texture, Vector2 pictureAA,
                      Point pointStart, Vector3 size) {
        this(texture, positionFromCell(pictureAA ,pointStart),
                new Vector2(), new Vector2(1,1), 0, 0, Color.WHITE, 4, 100, 0.01f,
                pictureAA, pointStart, size, new Color(1,1,0,1),
                new Color(1,0,1,1), new Color(0,1,1,1));
    }

    public Motionless(Point pointStart, Vector3 size,
                      Color topColor, Color leftColor, Color rightColor){
        this(null, new Vector2(), ///***
                new Vector2(0,0), new Vector2(1,1), 0, 0, Color.WHITE, 0, 100, 0.01f,
                new Vector2(0,0), pointStart, size, topColor, leftColor, rightColor);

    }

    public static Motionless makeHouse(Point pointStart, Vector3 size, Color topColor)
    {
        float left = 0.8f;
        float right = 0.6f;
        return new Motionless(pointStart, size, topColor,
                new Color(topColor.r * left, topColor.g * left, topColor.b * left, topColor.a * left),
                new Color(topColor.r * right, topColor.g * right, topColor.b * right, topColor.a * right));
    }

    public void setAnimationTextures(Texture[] textures)
    {
        this.textures = textures;
    }

    /**
     * Анімація обєкту
     */
    private void animation() {
        if (textures!=null)
            texture = textures[(int) ((MainGame.counter / speedAnimation) % textures.length)];
    }

    /**
     * Зробити інформацію про обєкт
     */
    public void makeInfo() {

        if (pointStart!= null)
            info = //"texture: " +  texture.toString() + "\n" +
                    "position: " + position.toString() + "\n" +
                    "positionPoint: " + pointStart.toString() + "\n" +
                    "size: " + size.x+","+size.x+","+size.x+"," + "\n" +
                /*"origin: " + origin.toString() + "\n" +
                "wh: " + wh.toString() + "\n" +
                "scale: " + scale.toString() + "\n" +
                "src: " + src.toString() + "\n" +
                "srcWH: " + srcWH.toString() + "\n" +
                "rotation: " + rotation + "\n" +
                "flipX: " + flipX + "\n" +
                "flipY: " + flipY + "\n" +*/
                    "layer: " + layer + "\n" +
                    "color: " + color.toString();
    }

    /**
     * Обновка ігрового стану
     */
    @Override
    public void update() {
        if (lifeNow < lifeMax) lifeNow += healing; // Жизняка по трохи востановлюється
        else lifeNow = lifeMax;
        animation(); // Анімація якщо є
        super.update();
    }

    /**
     * Переміщає картинку таким чином щоб її реальний початок(а не прозора пустота)
     * були в початку координат
     * @param pictureAA
     * @param pointStart
     * @return
     */
    public static Vector2 positionFromCell(Vector2 pictureAA, Point pointStart) {
        Vector2 positionInReal
                = new Vector2(pointStart.x * newCoordX - pointStart.y * newCoordX - pictureAA.x,
                pointStart.x * newCoordY + pointStart.y * newCoordY - pictureAA.y);
        return positionInReal;
    }

    /**
     * Функціональна промальовка куба
     * @param spriteBatch
     * @param shapeRenderer
     */
    @Override
    public void trueDraw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer)
    {
        if (textures == null) // Для флетартних картинок
        {
            float downNearX = pointStart.x * newCoordX - pointStart.y * newCoordX;
            float downNearY = pointStart.x * newCoordY + pointStart.y * newCoordY;
            float leftDownX = downNearX - size.y * newCoordX;
            float leftDownY = downNearY + size.y * newCoordY;

            float middleX = downNearX;
            float middleY = downNearY + newCoordZ * size.z;
            float leftUpX = leftDownX;
            float leftUpY = leftDownY + newCoordZ * size.z;

            float rightDownX = downNearX + size.x * newCoordX;
            float rightDownY = downNearY + size.x * newCoordY;
            float rightUpX = rightDownX;
            float rightUpY = rightDownY + newCoordZ * size.z;

            float farUpX = leftUpX + size.x * newCoordX;
            float farUpY = leftUpY + size.x * newCoordY;

            shapeRenderer.setColor(leftColor.r * color.r ,leftColor.g * color.g, leftColor.b * color.b, leftColor.a * color.a);
            shapeRenderer.triangle(downNearX, downNearY, leftDownX, leftDownY, middleX, middleY);
            shapeRenderer.triangle(leftDownX, leftDownY, middleX, middleY, leftUpX, leftUpY);
            shapeRenderer.setColor(rightColor.r * color.r ,rightColor.g * color.g, rightColor.b * color.b, rightColor.a * color.a);
            shapeRenderer.triangle(downNearX, downNearY, rightDownX, rightDownY, middleX, middleY);
            shapeRenderer.triangle(rightDownX, rightDownY, middleX, middleY, rightUpX, rightUpY);
            shapeRenderer.setColor(topColor.r * color.r ,topColor.g * color.g, topColor.b * color.b, topColor.a * color.a);
            shapeRenderer.triangle(middleX, middleY, farUpX, farUpY, leftUpX, leftUpY);
            shapeRenderer.triangle(middleX, middleY, farUpX, farUpY, rightUpX, rightUpY);
        }
        else
            super.trueDraw(spriteBatch, shapeRenderer);
    }
}
