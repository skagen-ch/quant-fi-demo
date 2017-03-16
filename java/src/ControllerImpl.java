import java.io.IOException;

public class ControllerImpl implements Controller {
	Pricer vanillaPricer;
	PricerView pricerView;
	
	public ControllerImpl(Pricer vanillaPricer) {
		this.vanillaPricer = vanillaPricer;
		pricerView = new PricerView(this, vanillaPricer);
		pricerView.setupFrame();
	}
	
	@Override
	public void calculate(String marketDataPath) throws NumberFormatException, IOException {
		vanillaPricer.calculate(marketDataPath);
	}

	@Override
	public void setValuationDate(String valuationDate) {
		vanillaPricer.setValuationDate(valuationDate);
	}

	@Override
	public void setSettlementDate(String settlementDate) {
		vanillaPricer.setSettlementDate(settlementDate);
	}

	@Override
	public void setFixedFrequency(String frq) {
		vanillaPricer.setFixedFrequency(frq);
	}

	@Override
	public void setFloatFrequency(String frq) {
		vanillaPricer.setFloatFrequency(frq);
	}

	@Override
	public void setNotional(String notional) {
		vanillaPricer.setNotional(notional);
	}

	@Override
	public void setTenor(String tenor) {
		vanillaPricer.setTenor(tenor);
	}

	@Override
	public void setCurrentFloat(String rate) {
		vanillaPricer.setCurrentFloat(rate);
	}

	@Override
	public void setSpread(String spread) {
		vanillaPricer.setSpread(spread);
	}

	@Override
	public void setNpv(String npv) {
		vanillaPricer.setNpv(npv);
	}
}
