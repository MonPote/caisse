package Service.CaisseService;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by patrickear on 16/1/2017.
 */
public class Promotion {
    private String productId;
    private String promotionName;
    private String promotionFormula;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
