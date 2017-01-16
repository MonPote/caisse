package Service.CaisseService;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by patrickear on 16/1/2017.
 */
public class Coupon {
    private String couponId;
    private int couponQuantity;
    private String customerId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
