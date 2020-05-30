package calendarServer.server.listeners;

import static calendarServer.Main.app;
import calendarServer.database.DataHandler;
import calendarServer.database.Employee;
import calendarServer.database.Vacation;
import calendarServer.server.NetworkData;
import calendarServer.server.NetworkData.CreateVacationRequest;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author sreis
 */
public class VacationListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof NetworkData.EditVacationRequest){
            NetworkData.EditVacationRequest request = (NetworkData.EditVacationRequest)object;
            
            Employee friseur = app.handler.root.friseure.get(request.workerId);
            
            Vacation vacation = new Vacation();
            vacation.start = request.start;
            vacation.end = request.end;
            vacation.id = request.id;
            
            friseur.vacations.put(vacation.id, vacation);
            app.handler.writeFile();
            app.server.sendToAllTCP(friseur);
        }else if(object instanceof NetworkData.RemoveVacationRequest){
            NetworkData.RemoveVacationRequest request = (NetworkData.RemoveVacationRequest)object;
            app.handler.root.friseure.get(request.workerId).vacations.remove(request.id);
            app.handler.writeFile();
            app.server.sendToAllTCP(app.handler.root.friseure.get(request.workerId));
        }else if(object instanceof NetworkData.CreateVacationRequest){
            Employee friseur = app.handler.root.friseure.get(((CreateVacationRequest) object).workerId);
            Vacation vacation = new Vacation(DataHandler.nextId(friseur.vacations), LocalDate.now(), LocalDate.now());
            friseur.vacations.put(vacation.id, vacation);
            app.handler.writeFile();
            connection.sendTCP(friseur);
        }
    }
}
