
public class FixedCouponList extends CouponList {

	@Override
	void setCurrentFloatRate(Double currentFloat) { }

	@Override
	void setFixedRate(Double fixedRate) {
		if (fixedRate == null) return;
		couponList.forEach(x -> x.setForwardRate(fixedRate));
	}

}
