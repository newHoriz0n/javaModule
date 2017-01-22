package exe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainMausHandler mmh;

	public MainPanel(MainMausHandler mmh)
	{
		this.mmh = mmh;
	}

	public void paint(Graphics g)
	{
		BufferedImage bufferedImage = new BufferedImage(1400, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bufferedImage.createGraphics();

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);

		updatePanel(g2d);

		Graphics2D g2dComponent = (Graphics2D) g;
		g2dComponent.setColor(new Color(245, 245, 245));
		g.fillRect(0, 0, 1400, 800);
		g2dComponent.drawImage(bufferedImage, null, 0, 0);

	}

	public void mausHandlerEinstellen()
	{
		mmh.setPanelGroesse(getWidth(), getHeight());
		addMouseListener(mmh);
		addMouseMotionListener(mmh);
	}

	public void amSpielerAusrichten(Graphics2D g2d, double spielerPosX, double spielerPosY, double spieler_dir)
	{

		AffineTransform at = new AffineTransform();
		at.translate(getWidth() / 2 - spielerPosX, getHeight() / 2 - spielerPosY);
		at.rotate(spieler_dir, spielerPosX, spielerPosY);
		g2d.setTransform(at);
	}

	public void updatePanel(Graphics2D g2d)
	{
		mmh.updateMausBewegung();
	}

}
