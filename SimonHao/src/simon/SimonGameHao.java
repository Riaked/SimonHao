package simon;

import guiPractice.GUIApplication;

public class SimonGameHao extends GUIApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2941468541286501763L;

	public SimonGameHao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initScreen() {
		SimonScreenHao ssh = new SimonScreenHao(getWidth(), getHeight());
		setScreen(ssh);

	}

	public static void main(String[] args) {
		SimonGameHao game = new SimonGameHao();
		Thread app = new Thread(game);
		app.start();

	}

}
