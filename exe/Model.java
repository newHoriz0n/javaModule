package exe;

public abstract class Model
{

	private boolean strukturGeaendert;
	
	public abstract void update();
	
	public void setStrukturGeaendert(boolean geandert) {
		this.strukturGeaendert = geandert;
	}
	
	public boolean isStrukturGeaendert() {
		return strukturGeaendert;
	}
	
}
