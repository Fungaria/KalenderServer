package calendarServer.database.adapters;

import calendarServer.database.Vacation;

/**
 *
 * @author sreis
 */
public class VacationAdapter extends MapAdapter<Vacation>{
    public VacationAdapter() {
        super(Vacation[]::new);
    }
}
