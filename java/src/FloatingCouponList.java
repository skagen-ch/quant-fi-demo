
public class FloatingCouponList extends CouponList {

	@Override
	void setCurrentFloatRate(Double currentFloat) {
		if (currentFloat == null) return;
		if (currentFloat > 0)
        {
            couponList.get(0).setForwardRate(currentFloat);
        }
	}

	@Override
	void setFixedRate(Double fixedRate) { }

}
