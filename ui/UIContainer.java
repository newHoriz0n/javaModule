package ui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import exe.Drawable;

public abstract class UIContainer implements Drawable
{

	protected List<UIElement> uiElemente;
	protected List<UIContainer> uiContainer;
	protected int xOffset;
	protected int yOffset;

	private int currentElementIndex;

	public UIContainer()
	{
		this.uiElemente = new ArrayList<UIElement>();
		this.uiContainer = new ArrayList<UIContainer>();
		this.currentElementIndex = 0;
	}

	public abstract void loadUIElemente();

	public void setOffset(int offx, int offy)
	{
		this.xOffset = offx;
		this.yOffset = offy;
	}

	public void addUIElement(UIElement element)
	{
		element.setID(currentElementIndex);
		this.uiElemente.add(element);
		this.currentElementIndex++;
	}

	public void clearUIElemente()
	{
		this.uiElemente.clear();
	}

	protected void addUIContainer(UIContainer uic)
	{
		this.uiContainer.add(uic);
	}

	public int getXOffset()
	{
		return xOffset;
	}

	public int getYOffset()
	{
		return yOffset;
	}

	public void update()
	{
		for (UIElement uie : uiElemente) {
			uie.update();
		}

		for (UIContainer uic : uiContainer) {
			uic.update();
		}
	}

	public boolean checkMausClick(int button, int mausX, int mausY)
	{

		boolean treffer = false;

		mausX -= xOffset;
		mausY -= yOffset;

		for (UIContainer uic : uiContainer) {
			if (uic.checkMausClick(button, mausX, mausY)) {
				treffer = true;
			}
		}

		for (UIElement uie : uiElemente) {
			if (uie.checkMausKlick(mausX, mausY)) {
				uie.mausAktionenAusfuehren();
				treffer = true;
			}
		}

		return treffer;

	}

	@Override
	public void paint(Graphics2D g2d, ImageObserver obs)
	{
		double altTranslateX = g2d.getTransform().getTranslateX();
		double altTranslateY = g2d.getTransform().getTranslateY();

		AffineTransform at = new AffineTransform();
		at.translate(altTranslateX + xOffset, altTranslateY + yOffset);
		g2d.setTransform(at);

		for (UIElement uie : uiElemente) {
			uie.paint(g2d, obs);
		}

		for (UIContainer uic : uiContainer) {
			uic.paint(g2d, obs);
		}

		AffineTransform at2 = new AffineTransform();
		at2.translate(altTranslateX, altTranslateY);
		g2d.setTransform(at2);
	}

}
