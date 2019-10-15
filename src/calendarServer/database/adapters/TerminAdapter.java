package calendarServer.database.adapters;

import calendarServer.database.Termin;

/**
 *
 * @author sreis
 */
public class TerminAdapter extends MapAdapter<Termin>{
    public TerminAdapter() {
        super(Termin[]::new);
    }
}
