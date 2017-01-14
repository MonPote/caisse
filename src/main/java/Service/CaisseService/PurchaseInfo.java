package Service.CaisseService;

import java.util.List;

/**
 * Created by patrickear on 14/1/2017.
 */
public class PurchaseInfo {
    private String receiptId;
    private String customerId;
    private List<Product> products;

    public PurchaseInfo(String receiptId, String customerId, List<Product> products) {
        this.receiptId = receiptId;
        this.customerId = customerId;
        this.products = products;
    }
}
