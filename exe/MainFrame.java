package exe;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Model m;
	protected MainPanel p;
	protected boolean fertig_geladen;
	private MainTastenHandler mth;

	private long frameTime;

	public MainFrame(Model m2, MainPanel p2, MainTastenHandler mth)
	{

		 setUndecorated(true);
		 setExtendedState(MAXIMIZED_BOTH);
		setSize(1366, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		this.m = m2;
		this.p = p2;
		this.mth = mth;

		add(p);

		addKeyListener(mth);

		UpdateThread ut = new UpdateThread(p2, this);
		ut.start();

	}

	public MainFrame(Model m2, MainPanel p2)
	{
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		this.m = m2;
		this.p = p2;
		add(p);

		UpdateThread ut = new UpdateThread(p2, this);
		ut.start();
	}

	public void frameEinstellen()
	{
		if (p.getWidth() > 0 && p.getHeight() > 0) {
			p.mausHandlerEinstellen();
			fertig_geladen = true;
		}
	}

	public void setFrameTime(long time)
	{
		this.frameTime = time;
	}

	public boolean isFertigGeladen()
	{
		return fertig_geladen;
	}

	public Model getModel()
	{
		return m;
	}

	public MainTastenHandler getMainTastenHandler()
	{
		return mth;
	}

	class UpdateThread extends Thread
	{

		private JPanel p;
		private MainFrame mf;

		public UpdateThread(JPanel p2, MainFrame mf)
		{
			this.p = p2;
			this.mf = mf;
		}

		public void run()
		{
			while (!mf.isFertigGeladen()) {
				mf.frameEinstellen();
			}
			while (true) {
				if (mf.getMainTastenHandler() != null) {
					mf.getMainTastenHandler().calcTastenFunktionen();
				}
				mf.getModel().update();
				p.updateUI();
				try {
					sleep(frameTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
}
