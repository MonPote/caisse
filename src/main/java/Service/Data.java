package Service;


/**
 * Created by Hugo Capes on 01/10/2016.
 */
public class Data {

    private String address;
    Agenda[] agenda;

    public Data(String addressIn, Agenda[] agendaArrayIn) {
        this.address = addressIn;
        this.agenda = agendaArrayIn;
    }
}

   // String[][] agendaArray = new String[][]{{"date", "repeat", "callback"}, {"09:00", "5", "..."}};






