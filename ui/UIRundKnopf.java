package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class UIRundKnopf extends UIElement
{
	protected int x;
	protected int y;
	protected int radius;

	private Color rahmen;
	private Color hintergrund;
	private Color text;

	public UIRundKnopf(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;

		this.rahmen = Color.BLACK;
		this.hintergrund = new Color(255,255,255,50);
	}

	@Override
	public boolean checkMausKlick(int mausX, int mausY)
	{
		double dx = mausX - x;
		double dy = mausY - y;
		double dist = Math.sqrt(dx * dx + dy * dy);
		if (dist <= radius) {
			return true;
		}
		return false;
	}

	public void setHintergrundFarbe(Color hintergrund)
	{
		this.hintergrund = hintergrund;
	}

	public void setRahmenFarbe(Color rahmen)
	{
		this.rahmen = rahmen;
	}

	@Override
	public void paint(Graphics2D g2d, ImageObserver obs)
	{
		g2d.setColor(hintergrund);
		g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		g2d.setColor(rahmen);
		g2d.drawOval(x - radius, y - radius, radius * 2, radius * 2);
	}

}
