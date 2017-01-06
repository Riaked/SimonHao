package simon;

import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface ButtonInterfaceHao extends Clickable {

	void setColor(Color color);

	void setX(int i);

	void setY(int i);

	public void setAction(Action a);

	void highlight();

	void dim();
}
