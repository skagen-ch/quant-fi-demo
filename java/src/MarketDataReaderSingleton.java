import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MarketDataReaderSingleton {
	private static MarketDataReaderSingleton reader;
	private FileReader fr;
	private BufferedReader br;
	
	private MarketDataReaderSingleton(String marketDataPath) throws FileNotFoundException{
		fr = new FileReader(marketDataPath);
		br = new BufferedReader(fr);
	}
	
	public static synchronized MarketDataReaderSingleton getInstance(String marketDataPath) throws FileNotFoundException {
		if (reader == null) {
			reader = new MarketDataReaderSingleton(marketDataPath);
		}
		return reader;
	}
	
	public String readLine() throws IOException {
		return br.readLine();
	}
	
	public void close() throws IOException {
		br.close();
	}
}
