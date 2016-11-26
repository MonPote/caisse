package Service.ServicesKit;

import Service.Agenda;
import Service.Data;


/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class Handshake {
    private static Handshake INSTANCE = null;
    private String sender;
    private int instanceID;
    private Data data;

    private Handshake(String senderIn, int instanceIDIn, String adressIn, Agenda[] agendaIn) {
        this.sender = senderIn;
        this.instanceID = instanceIDIn;
        this.data = new Data(adressIn, agendaIn);
    }

    public static Handshake getInstance(String senderIn, int instanceIDIn, String adressIn, Agenda[] agendaIn) {
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






