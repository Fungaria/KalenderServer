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
            addBlock(request);
        }else if(object instanceof NetworkData.RemoveBlockRequest){
            removeBlock(((NetworkData.RemoveBlockRequest) object).id);
            app.server.sendToAllTCP(object);
        }
    }
    
    private void addBlock(NetworkData.BlockRequest request){
        Blockierung blockierung = new Blockierung();
        blockierung.duration = request.duration;
        blockierung.start = request.start;
        blockierung.id = app.handler.nextBlockId();
        blockierung.friseur = request.friseur;
        blockierung.msg = request.msg;

        app.handler.root.blockierungen.add(blockierung);
        app.handler.writeFile();

        app.server.sendToAllTCP(blockierung);
    }
    
    private void removeBlock(int id){
        app.handler.root.blockierungen.removeIf((t) -> t.id==id);
        app.handler.writeFile();
    }
}
