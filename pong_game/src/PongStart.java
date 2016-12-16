import controller.Controller;
import view.MenueView;

public class PongStart {

	public static void main(String[] args){
		MenueView pong = new MenueView("Pong - The Game");
		pong.init(new Controller());
		new Thread().start();
	}  
}
