package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public class UIText extends UIElement
{

	private String text;
	private Color textFarbe;
	private Font textFont;
	private int x;
	private int y;

	public UIText(int x, int y, String text)
	{
		this.x = x;
		this.y = y;
		this.text = text;
		this.textFont = new Font("Arial", Font.PLAIN, 12);
		this.textFarbe = Color.WHITE;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public void paint(Graphics2D g2d, ImageObserver obs)
	{
		g2d.setColor(textFarbe);
		g2d.setFont(textFont);
		g2d.drawString(text, x, y);
	}

	@Override
	public boolean checkMausKlick(int mausX, int mausY)
	{
		return false;
	}

	public String getText()
	{
		return text;
	}

}
