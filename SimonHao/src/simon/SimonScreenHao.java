package simon;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;

public class SimonScreenHao extends ClickableScreen implements Runnable {

	private ProgressInterfaceHao progress;
	private ArrayList<MoveInterfaceHao> move;
	private ButtonInterfaceHao[] button;
	private int roundNumber;
	private boolean inputSubmitted;
	private TextLabel label;
	private int sequenceNumber;
	private int previousButton;
	
	public SimonScreenHao(int width, int height) {
		super(width, height);
		Thread play = new Thread(this);
		play.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects){
		move = new ArrayList<MoveInterfaceHao>();
		addButtons(viewObjects);
		progress = getProgress();
		label = new TextLabel(220, 60, 300, 40, "This is Simon's game");
		previousButton = -1;
		move.add(randomMove());
		move.add(randomMove());
		previousButton = 0;
		viewObjects.add(label);
		viewObjects.add(progress);
	}
	
	public MoveInterfaceHao randomMove() {
		int rand;
		rand = (int) (Math.random() * button.length);
		while (rand == previousButton){
			rand = (int) (Math.random() * button.length);
		}
		previousButton = rand;
		return new Move(button[rand]);
	}

	public void addButtons(ArrayList<Visible> viewObjects) {
		int numberOfButtons = 5;
		button = new ButtonInterfaceHao[numberOfButtons];
		Color[] colors = {Color.red, Color.orange, Color.cyan, Color.green, Color.white};
		for (int i = 0; i < numberOfButtons; i++){
			button[i] = getAButton();
			button[i].setColor(colors[i]);
			button[i].setX(380 + (int)(100*Math.cos(i*2*Math.PI/(numberOfButtons))));
			button[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(numberOfButtons))));
			final ButtonInterfaceHao b = button[i];
			b.dim();
			b.setAction(new Action(){
				public void act() {
					if (inputSubmitted){
						Thread blink = new Thread(new Runnable(){
							public void run(){
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.dim();
							}
						});
						blink.start();
						if (inputSubmitted && move.get(sequenceNumber).getButton() == b){
							sequenceNumber++;
						}
						else{
							gameOver();
							return;
						}
						if (sequenceNumber == move.size()){
							Thread nextRound = new Thread(SimonScreenHao.this);
							nextRound.start(); 
						}
					}
					
				}
				
			});
			viewObjects.add(button[i]);
		}
	}

	private ButtonInterfaceHao getAButton() {
		return new Button();
	}

	public void gameOver(){
		progress.gameOver();
	}
	

	public ProgressInterfaceHao getProgress(){
		return new Progress();
	}
	
	@Override
	public void run() {
		label.setText("");
		nextRound();
	}

	private void playSequence() {
		ButtonInterfaceHao b = null;
		for(MoveInterfaceHao m: move){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}

	private void nextRound() {
		inputSubmitted = false;
		roundNumber++;
		progress.setRound(roundNumber);
		move.add(randomMove());
		progress.setSequenceLength(move.size());
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");
		inputSubmitted = true;
		sequenceNumber = 0;
	}

	private void changeText(String s){
		try{
			label.setText(s);
			Thread.sleep(1000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
}
