package Service;

import Service.ServicesKit.TrueData;
import Service.ServicesKit.WebService;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Created by Steven on 02/11/2016.
 */
public class ParseFunction {
    String fct = "";
    Data data = new Data("", new Agenda[0]);

    public ParseFunction(String fct, Data data) {
        this.fct = fct;
        this.data = data;
    }

    public String getFct() {
        return fct;
    }

    public void setFct(String fct) {
        this.fct = fct;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String execute() {
        switch (fct) {
            /* SWITCH SUR LE NON DE LA FONCTION POUR EXECUTER LA BONNE FONCTION */
            case "WebService": {
                String sender = "back_office";
                String message = "test back_office";
                String result = "it worked!";
                return new Gson().toJson(result);
            }

            case "toto": {
                String sender = "back_office";
                String message = "test back_office";
                String result = "it worked!";
                Success test = new Success(message, result);
                //String data = new Gson().toJson(test);
                WebService self = new WebService(sender, 1, new TrueData(new Gson().toJson(test)));

                System.out.println("WebService with toto called: " + self.toString());

                return new Gson().toJson(self);
            }

            /*
            case "EN-GC1" :
                String sender = "entrepot";
                String message = MESSAGE
                String result = "RESULTAT DE LA FONCTION LIE AU CASE"
                return new Gson().toJson(self);
             */

            default: {
                System.out.println("Unknown function: " + fct);
                break;
            }
        }

        return new Gson().toJson("msg");
    }
}
