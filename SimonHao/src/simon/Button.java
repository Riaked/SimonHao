package simon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class Button extends Component implements ButtonInterfaceHao {

	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private Action act;
	private Color c;
	private Color dispColor;
	private boolean highlight;
	
	public Button() {
		super(0,0,WIDTH,HEIGHT);
	}

	@Override
	public void highlight() {
		if(c != null) dispColor = c;
		highlight = true;
		update();
	}

	@Override
	public void dim() {
		dispColor = Color.gray;
		highlight = false;
		update();
	}

	@Override
	public void setAction(Action action) {
		this.act = action;
	}

	@Override
	public void act() {
		act.act();
		
	}

	@Override
	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+WIDTH/2), 2)+Math.pow(y-(getY()+HEIGHT/2), 2));
		return distance < WIDTH/2;
	}

	@Override
	public void setColor(Color color) {
		this.c = color;
		dispColor = c;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(dispColor != null) g.setColor(dispColor);
		else g.setColor(Color.gray);
		g.fillOval(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawOval(0, 0, WIDTH-1, HEIGHT-1);
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			int s = (int)(5/8.0 * WIDTH);
			int t = (int)(1.0/5*HEIGHT)+4;
			p.addPoint(s-4, t-4);
			p.addPoint(s+7, t-2);
			p.addPoint(s+10, t);
			p.addPoint(s+14, t+10);
			p.addPoint(s+12, t+14);
			p.addPoint(s+8, t+3);
			g.fill(p);
		}
	}


	private String name;
	public void setName(String s){
		this.name = s;
	}
	
	public String toString(){
		return name;
	}
}