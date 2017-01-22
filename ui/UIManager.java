package ui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import exe.Drawable;
import exe.MainFrame;
import exe.Model;

public abstract class UIManager implements Drawable
{

	private List<UIContainer> uics;

	protected int xOffset;
	protected int yOffset;

	protected Model m;
	protected MainFrame mf;
	
	public UIManager(Model m)
	{
		this.uics = new ArrayList<UIContainer>();
		this.m = m;
		
		loadUIStruktur();
	}
	
	public void setMainFrame(MainFrame mf) {
		this.mf = mf;
	}

	public void addUIContainer(UIContainer uic)
	{
		this.uics.add(uic);
	}
	
	public abstract void loadUIStruktur();

	public boolean checkMausClick(int button, int mausX, int mausY)
	{
		
		List<UIContainer> bestand = new ArrayList<UIContainer>();
		bestand.addAll(uics);

		boolean treffer = false;
		
		for (UIContainer uic : bestand) {
			if(uic.checkMausClick(button, mausX, mausY)) {
				treffer = true;
			}
		}
		
		return treffer;
	}

	public void setxOffset(int xOffset)
	{
		this.xOffset = xOffset;
	}

	public void setyOffset(int yOffset)
	{
		this.yOffset = yOffset;
	}

	public void update()
	{
		if(m.isStrukturGeaendert()) {
			clearUI();
			loadUIStruktur();
		}
		
		for (UIContainer uic : uics) {
			uic.update();
		}
		m.setStrukturGeaendert(false);
	}

	private void clearUI()
	{
		this.uics.clear();
	}

	@Override
	public void paint(Graphics2D g2d, ImageObserver obs)
	{
		AffineTransform at = new AffineTransform();
		at.translate(xOffset, yOffset);
		g2d.setTransform(at);

		for (UIContainer uic : uics) {
			uic.paint(g2d, obs);
		}

		AffineTransform at2 = new AffineTransform();
		at2.translate(-xOffset, -yOffset);
		g2d.setTransform(at2);
	}

}
