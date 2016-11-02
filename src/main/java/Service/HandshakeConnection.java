package Service;

import model.Entities.ProductEntity;
import model.dao.ProductDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class HandshakeConnection {


    public HandshakeConnection() {
    }

    public static String post(String adress, String message) throws IOException {
            String result = "";
            OutputStreamWriter writer = null;
            BufferedReader reader = null;
            try {
//encodage des paramètres de la requête
                String data= message;

//création de la connection
                URL url = new URL(adress);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);


//envoi de la requête
                writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(data);
                writer.flush();




//lecture de la réponse
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    result+=ligne;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }finally{
                try{writer.close();}catch(Exception e){}
                try{reader.close();}catch(Exception e){}
            }
            return result;
        }

    }

