package Service.CaisseService;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by patrickear on 14/1/2017.
 */
public class Product {
    private String productRef;
    private int quantity;

    public Product(String productRef, int quantity) {
        this.productRef = productRef;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
