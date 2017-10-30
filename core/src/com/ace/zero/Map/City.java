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
        Motionless[] motionlesses = new Motionless[6];

        motionlesses[0] = new Motionless(new Point(0,0), new Vector3(4000,4000,0),
                new Color(0.8f, 0.8f, 0.8f, 1), Color.BROWN, Color.BLACK);
        motionlesses[1] = new Motionless(new Point(16, 4), new Vector3(16, 32, 32),
                new Color(0.5f, 0.5f, 0.5f, 1),new Color(0.3f, 0.3f, 0.3f, 1),new Color(0.1f, 0.1f, 0.1f, 1));

        return motionlesses;
    }
}
