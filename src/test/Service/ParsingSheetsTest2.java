package Service;

import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Hugo Capes on 30/09/2016.
 */
public class ParsingSheetsTest2 {

    @Test
    public void shippingParsingTest() throws ParseException {

        ParsingSheets com = new ParsingSheets();
        InputStream myStream = null;
        String fichier = "livraison2.json";

        String chaine = "";

        //lecture du fichier texte
        try {
            InputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
                chaine += ligne + "\n";
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.print(chaine);
        JSONObject sheet = new JSONObject(chaine);
        int[][] items = com.parseShippingSheet(sheet);
        assertEquals(items[0][0], 1001);
        assertEquals(items[0][1], 2);
        assertEquals(items[1][0], 1002);
        assertEquals(items[1][1], 3);
        assertEquals(items[2][0], 1003);
        assertEquals(items[2][1], 6);
        assertEquals(items[3][0], 1004);
        assertEquals(items[3][1], 1);


    }
}

