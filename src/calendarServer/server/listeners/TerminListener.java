package calendarServer.server.listeners;

import static calendarServer.Main.app;
import calendarServer.database.Termin;
import calendarServer.server.NetworkData;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author sreis
 */
public class TerminListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof NetworkData.TerminRequest) {
            Termin termin = createTermin((NetworkData.TerminRequest) object);
            app.handler.root.termine.add(termin);
            updateDatabase();
            app.server.sendToAllTCP(termin);
        }else if(object instanceof NetworkData.StornoRequest){
            deleteTermin(((NetworkData.StornoRequest) object).id);
            updateDatabase();
            app.server.sendToAllTCP(object);
        }
    }

    private void deleteTermin(int id){
        app.handler.root.termine.removeIf((t) -> t.id==id);
    }
    
    private void updateDatabase(){
        try {
            app.handler.writeFile();
        } catch (JAXBException ex) {
            Logger.getLogger(TerminListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Termin createTermin(NetworkData.TerminRequest request) {
        Termin termin = new Termin();
        termin.dauer = request.duration;
        termin.friseur = request.friseurId;
        termin.id = app.handler.nextTerminId();
        termin.kundenid = request.kundenId;
        termin.service = request.serviceId;
        termin.start = request.start;
        termin.urheber = request.urheber;
        termin.erstellt = new Date();
        System.out.println(termin);
        return termin;
    }
}
