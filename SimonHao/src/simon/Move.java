package simon;

public class Move implements MoveInterfaceHao {

	private ButtonInterfaceHao b;
	
	public Move(ButtonInterfaceHao b){
		this.b = b;
	}
	
	@Override
	public ButtonInterfaceHao getButton() {
		return b;
	}

}
