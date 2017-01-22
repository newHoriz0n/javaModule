package exe;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public interface Drawable
{

	public abstract void paint(Graphics2D g2d, ImageObserver obs);
	
}
