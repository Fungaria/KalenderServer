package calendarServer.server.listeners;

import static calendarServer.Main.app;
import calendarServer.database.Blockierung;
import calendarServer.server.NetworkData;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author sreis
 */
public class BlockListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof NetworkData.BlockRequest){
            NetworkData.BlockRequest request = (NetworkData.BlockRequest)object;
            
            Blockierung blockierung = new Blockierung();
            blockierung.duration = request.duration;
            blockierung.start = request.start;
            blockierung.friseur = request.friseur;
            blockierung.msg = request.msg;
            
            app.handler.root.blockierungen.add(blockierung);
            app.handler.writeFile();
            
            app.server.sendToAllTCP(blockierung);
        }
    }
}
