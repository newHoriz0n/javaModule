package gfx;

import java.awt.Color;
import java.awt.Graphics2D;

import math.Vektor3D;

public class Lebensanzeige
{

	public static void paintLebensanzeige(Graphics2D g2d, Vektor3D relpos, double breite, double lebensanteil)
	{
		g2d.setColor(Color.RED);
		g2d.fillRect(relpos.getIntX(), relpos.getIntY(), (int) breite, 2);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(relpos.getIntX(), relpos.getIntY(), (int) (breite * lebensanteil), 2);

	}

}
