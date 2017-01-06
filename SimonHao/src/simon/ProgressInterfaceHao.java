package simon;

import guiPractice.components.Visible;

public interface ProgressInterfaceHao extends Visible {

	public void gameOver();

	public void setRound(int roundNumber);

	public void setSequenceLength(int size);
}
