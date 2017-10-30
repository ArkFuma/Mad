package com.ace.zero.Map;

import com.ace.zero.Objects.Motionless;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

import java.awt.Point;

/**
 * Created by Satana on 09.10.2017.
 */

/**
 * Карта міста
  */
public class City
{
    /**
     * Карта міста номер 0
     * @return масив будівель, їх позиціонування
     */
    public static Motionless[] Map0()
    {
        Motionless[] motionlesses = new Motionless[100];

        // Площадка міста загальна
        motionlesses[0] = Motionless.makeHouse(new Point(0, 0), new Vector3(8000, 8000, 0), new Color(0.8f, 0.8f, 0.8f, 1));

        // Дороги

        // Розмітка

        // Доми
        motionlesses[1] = Motionless.makeHouse(new Point(16, 4), new Vector3(16, 32, 32), new Color(0.5f, 0.5f, 0.5f, 1));

        return motionlesses;
    }
}
