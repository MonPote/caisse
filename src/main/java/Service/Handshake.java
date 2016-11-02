package Service;

import org.hibernate.mapping.Array;
import org.json.*;


/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class Handshake {
    private static Handshake INSTANCE = null;
    private String sender;
    private String instanceID;
    private Data data;

    private Handshake(String senderIn, String instanceIDIn, String adressIn, Agenda[] agendaIn) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = new Data(adressIn, agendaIn);
    }

    public static Handshake getInstance(String senderIn, String instanceIDIn, String adressIn, Agenda[] agendaIn) {
        if (INSTANCE == null) {
            INSTANCE = new Handshake(senderIn, instanceIDIn, adressIn, agendaIn);
        }
        return INSTANCE;
    }
/*
            agendaArray[][]
            agendaArray[0][0]=;
            agendaArray[0][1]="repeat";
            agendaArray[0][1]="callback";
            agendaArray[1][0]="09:00";
            agendaArray[1][1]="5";
            agendaArray[1][2]="...";
            */
}






