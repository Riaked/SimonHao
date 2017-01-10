package simon;

import guiPractice.GUIApplication;

public class SimonGameHao extends GUIApplication {
	
	public SimonGameHao() {
		super();
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
