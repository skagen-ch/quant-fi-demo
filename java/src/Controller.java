import java.io.FileNotFoundException;
import java.io.IOException;

public interface Controller {
	public void calculate(String marketDataPath) throws FileNotFoundException, NumberFormatException, IOException;
	
	public void setValuationDate(String valuationDate);
	
	public void setSettlementDate(String settlementDate);
	
	public void setFixedFrequency(String frq);
	
	public void setFloatFrequency(String frq);
	
	public void setNotional(String notional);

	public void setTenor(String tenor);
	
	public void setCurrentFloat(String rate);

	public void setSpread(String spread);
	
	public void setNpv(String npv);
}
