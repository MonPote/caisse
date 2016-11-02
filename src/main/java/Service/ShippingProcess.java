package Service;

import model.Entities.ProductEntity;
import model.dao.ProductDao;

/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class ShippingProcess {


    void stockFilling (int[][] itemList, ProductDao stock) {
        for(int i = 0; i < itemList.length; i++) {
            ProductEntity current = stock.findById(Integer.toString(itemList[i][0]));
            current.setQuantite((int) current.getQuantite() + itemList[i][1]);
        }

    }
}
