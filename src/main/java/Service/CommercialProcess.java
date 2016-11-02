package Service;

import model.Entities.ProductEntity;
import model.dao.ProductDao;
import org.json.JSONObject;

/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class CommercialProcess {

    void commercialSheetVerification (int[][] itemList, ProductDao stock ) {
        //2 parties : 1/vérifier que les quantité du bon commercial sont correctes et remplissent les stocks
        // 2/ ajouter les éléments manquant dans la liste

        for (int i = 0; i < itemList.length; i++) {
            ProductEntity current = stock.findById(Integer.toString(itemList[i][0]));
            if ((int) current.getQuantite() + itemList[i][1] < (int) current.getStockMax()) {
                itemList[i][1] = (int) current.getStockMax() - (int) current.getQuantite();
            }
        }


        }
    }

