package Service.CaisseService;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by patrickear on 16/1/2017.
 */
public class CouponData {
    private String sender;
    private int instanceID;
    private String yourApp;
    private int yourInstance;
    private String date;
    private Coupon data;

    public Coupon getData() { return data; }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
