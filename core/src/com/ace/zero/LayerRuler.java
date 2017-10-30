package com.ace.zero;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
//import com.sun.java.util.jar.pack.Package;

/**
 * Created by NoOne on 06.10.2017.
 * Керувач промальокою, сортує слої промальовки
 */

public class LayerRuler
{
    /**
     * Стек промальовки
     */
    SpriteBatch spriteBatch;

    /**
     * Промальовка примітивів
     */
    ShapeRenderer shapeRenderer;

    /**
     * Всі поля які треба буде промалювати
     */
    GameObject[] field;

    /**
     * Одне поле для сортування
     */
    GameObject field0;

    int max; // Максимальний розмір промальовуваних картинок
    int counter; // Лічильник який показує останній заповнений обєкт

    public LayerRuler(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;
        counter = -1; // Лічильник на мінус -1 бо немає ніяких обєктів
        max = 1000; // Максимальна кількість обєктів які можна промалювати
        field = new GameObject[max];
    }

    public void dispose()
    {
        if (field0!=null)
            field0.dispose();
        if (field!=null)
            for (int i = 0; i < field.length; i++)
                if (field[i] != null) field[i].dispose();
    }

    /**
     * Додати новий обєкт в стек для промальовки
     * @param texture
     * @param position
     * @param origin
     * @param wh
     * @param scale
     * @param src
     * @param srcWH
     * @param rotation
     * @param flipX
     * @param flipY
     * @param layer
     * @param color
     */
    public void add(Texture texture, Vector2 position, Vector2 origin, Vector2 wh, Vector2 scale,
                    Vector2 src, Vector2 srcWH, float rotation, boolean flipX, boolean flipY, float layer,
                    Color color) {
        add(new GameObject(texture, position, origin, wh, scale,
                src, srcWH, rotation, flipX, flipY, layer, color));
    }

    /**
     * Додати новий обєкт в стек для промальовки
     * @param gameObject
     */
    public void add(GameObject gameObject)
    {
        if (counter < max - 1) {
            counter++;
            this.field[counter] = gameObject;
        }
        else return;
    }

    /**
     * Реальна промальовка всіх обєктів
     */
    public void draw() {
        //pyramidalSort();
        sort();
        for (int i = 0; i <= counter; i++) {

            field[i].trueDraw(spriteBatch, shapeRenderer);
            field[i] = null;
        }
        counter = -1;
        spriteBatch.setColor(Color.WHITE);
    }

    /*public void draw2() {
        pyramidalSort();
        for (int i = 0; i <= counter; i++) {
            if (field[i] instanceof Motionless)
            {
                ((Motionless)(field[i]))
            }
            else {
                field[i].trueDraw(spriteBatch);
                field[i] = null;
            }
        }
        counter = -1;
        spriteBatch.setColor(Color.WHITE);
    }*/

    /**
     * Звичайне сортування вставками
     */
    void sort() {
        for (int i = 0; i < counter; i++) {
            for (int j = i+1; j <= counter; j++) {
                if (field[i].layer > field[j].layer) {
                    field0 = field[i];
                    field[i] = field[j];
                    field[j] = field0;
                }
            }
        }
    }

    /**побудова двійкової кучі (не зростаюче бінарне дерево) із масиву*/
    void makeHeap() {
        for(int i=0; i <= counter; i++)
        {
            // починаємо побудову із кінця масиву
            // тобто із останнього доданого елементу в дерево
            int index = i;
            while(index != 0){
                // знаходимо індекс батьківського запису
                int parentIndex = (index - 1) / 2;
                //рівняємо доченьку із батенькою
                if(field[index].layer <= field[parentIndex].layer)
                    break;

                //скидаємо отца свого із трону і займаємо його місце
                field0 = field[index];
                field[index] = field[parentIndex];
                field[parentIndex] = field0;

                //скіпетр переходить до нас
                index = parentIndex;
            }
        }
    }

    /**відновлює бінарне дерево після видалення з нього елементу*/
    public void restoreHeap(int counter) {
        //позиція вузла
        int index = 0;
        while(true){
            // індекси дочірніх записів у кучі (бінарному дереві)
            int childIndex1 = 2*index + 1;
            int childIndex2 = 2*index + 2;

            // якщо дочки випали із масиву то йдемо до батька
            if (childIndex1 > counter) childIndex1 = index;
            if (childIndex2 > counter) childIndex2 = index;

            // властивість бінарного дерева або кучі виконана виходимо
            if(field[childIndex1].layer <= field[index].layer &&
                    field[childIndex2].layer <= field[index].layer)
                break;

            // індекс більшого дочірнього запису
            int largerChild;
            if(field[childIndex1].layer > field[childIndex2].layer)
                largerChild = childIndex1;
            else
                largerChild = childIndex2;

            // міняємо місцями батька і найбільшу дочку
            field0 = field[index];
            field[index] = field[largerChild];
            field[largerChild] = field0;

            // переходимо до дочки
            // спускаємо індекс нижче по масиву для подальшої перевірки
            index = largerChild;
        }
    }

    /**сортування пірамідкою або кучею*/
    public void pyramidalSort(){
        // будуємо бінарне дерево
        makeHeap();
        // розмір бінарного дерева першопочатково рівний розміру масива
        int heapSize = counter;

        for (int i = counter; i > 0; i--){
            // міняємо місцями перший(найбільший) елемент і останній
            field0 = field[0];
            field[0] = field[i];
            field[i] = field0;

            // останній нам сортувати не потрібно тому його не чіпаємо
            heapSize--;

            restoreHeap(heapSize);
        }
    }
}
