package Service;

/**
 * Created by Xavier on 02/11/2016.
 */
public class Agenda {

    String date;
    Integer repeat;
    Integer callback;
    String[] agenda;


    public Agenda(String date, Integer repeat, Integer callback)
    {
        this.date = date;
        this.repeat = repeat;
        this.callback = callback;
    }

    public String[] sendAgenda()
    {
        agenda[0] = date;
        agenda[1] = repeat.toString();
        agenda[2] = callback.toString();
        return agenda;
    }
}
