package Service;
//import com.json.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Hugo Capes on 30/09/2016.
 */

//ref, qte et designation
public class ParsingSheets {

    //parse the shipping sheets sent from the wharehouse, doesn't cara about designation
    int[][] parseShippingSheet(JSONObject ship){
        JSONObject obj = ship.getJSONObject("Livraison");
        JSONArray array = obj.getJSONArray("items");
        int[][] items = new int[array.length()][2];
        for(int i=0; i < array.length(); i++){
            JSONObject ligne = array.getJSONObject(i);
            items[i][0] = ligne.getInt("ref");
            items[i][1] = ligne.getInt("Quantity");
        }

        return items;
    }


    //parse the commercial sheets from the commercial service
    int[][] parseCommercialSheet(JSONObject commercial) {
        JSONObject obj = commercial.getJSONObject("Réassort");
        JSONArray array = obj.getJSONArray("items");
        int[][] items = new int[array.length()][2];
        for(int i=0; i<array.length(); i++){
            JSONObject ligne = array.getJSONObject(i);
            items[i][0] = ligne.getInt("item_id");
            items[i][1] = ligne.getInt("Quantité");
        }

        return items;
    }
}
