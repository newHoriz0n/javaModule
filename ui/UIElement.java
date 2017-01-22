package ui;

import java.util.ArrayList;
import java.util.List;

import exe.Drawable;

public abstract class UIElement implements Drawable
{
	
	private List<UIAktion> aktionenBeiMausklick;
	private List<UIAktion> aktionenBeiUpdate;

	private int id;

	public UIElement()
	{
		this.aktionenBeiMausklick = new ArrayList<UIAktion>();
		this.aktionenBeiUpdate = new ArrayList<UIAktion>();
	}

	public void addMausAktion(UIAktion uiaktion)
	{
		aktionenBeiMausklick.add(uiaktion);
	}

	public void addUpdateAktion(UIAktion uiaktion)
	{
		this.aktionenBeiUpdate.add(uiaktion);
	}

	public abstract boolean checkMausKlick(int mausX, int mausY);

	public void mausAktionenAusfuehren()
	{
		for (UIAktion uia : aktionenBeiMausklick) {
			uia.aktionAusfuehren();
		}
	}
	
	public int getId()
	{
		return id;
	}

	public void update()
	{
		for (UIAktion uia : aktionenBeiUpdate) {
			uia.aktionAusfuehren();
		}
	}

	public void setID(int currentElementIndex)
	{
		this.id = currentElementIndex;
	}
}