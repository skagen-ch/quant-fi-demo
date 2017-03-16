import java.io.IOException;

public class Launcher {
	private static Controller controller;
	private static Pricer pricer;

	public static void main(String[] args) throws java.text.ParseException, NumberFormatException, IOException {
			pricer = new VanillaPricer();
			controller = new ControllerImpl(pricer);
	}
}
