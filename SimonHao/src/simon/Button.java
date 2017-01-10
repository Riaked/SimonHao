package simon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class Button extends Component implements ButtonInterfaceHao {

	private static final int WIDTH = 60;
	private static final int HEIGHT = 60;
	private Action act;
	private Color c;
	private Color dispColor;
	
	public Button() {
		super(0,0,WIDTH,HEIGHT);
	}

	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+WIDTH/2), 2)+Math.pow(y-(getY()+HEIGHT/2), 2));
		return distance < WIDTH/2;
	}

	public void act() {
		act.act();
	}



	public void setColor(Color color) {
		this.c = color;
		dispColor = c;
		update();
	}

	public void highlight() {
		if(c != null) dispColor = c;
		update();
	}

	public void dim() {
		dispColor = Color.gray;
		update();
	}

	public void setAction(Action action) {
		this.act = action;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(dispColor != null) g.setColor(dispColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawOval(0, 0, WIDTH-1, HEIGHT-1);
	}

}
