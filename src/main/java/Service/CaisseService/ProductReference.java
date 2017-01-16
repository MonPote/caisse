package Service.CaisseService;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by patrickear on 16/1/2017.
 */
public class ProductReference {
    List<Product> listProduct;

    public ProductReference(List<Product> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
