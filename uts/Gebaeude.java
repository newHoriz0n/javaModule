package uts;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import exe.Drawable;
import math.Vektor3D;

public class Gebaeude extends Collidable implements Drawable
{

	private BufferedImage img;
	private Vektor3D imgOffset;

	public Gebaeude(Vektor3D pos, List<Collidable> cs, double ausrichtung)
	{
		super(pos, -1, cs);
		this.ausrichtung = ausrichtung;
	}

	public void setGebaeudeImage(String imgSrc, Vektor3D imgOffset)
	{
		try {
			this.img = ImageIO.read(new File(imgSrc));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.imgOffset = new Vektor3D(imgOffset);
		setFixiert(true);
	}
	
	@Override
	public void update()
	{
		super.update();
	}

	@Override
	public void paint(Graphics2D g2d, ImageObserver obs)
	{
		AffineTransform at = new AffineTransform();
		at.rotate(-ausrichtung, position.getIntX(), position.getIntY());
		at.translate(position.getIntX() - imgOffset.getIntX(), position.getIntY() - imgOffset.getIntY());
		g2d.drawImage(img, at, obs);
	}

}
