package Service;

/**
 * Created by Xavier on 02/11/2016.
 */
public class Agenda {

    String ag_date;
    Integer ag_repeat;
    Integer ag_callback;
    String[] agenda;


    public Agenda(String date, Integer repeat, Integer callback)
    {
        ag_date = date;
        ag_repeat = repeat;
        ag_callback = callback;
    }

    public String[] sendAgenda()
    {
        agenda[0] = ag_date;
        agenda[1] = ag_repeat.toString();
        agenda[2] = ag_callback.toString();
        return agenda;
    }
}
