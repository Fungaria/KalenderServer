package calendarServer.server.listeners;

import static calendarServer.Main.app;
import calendarServer.database.Blockierung;
import calendarServer.database.DataHandler;
import calendarServer.server.NetworkData;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import jdk.nashorn.internal.ir.Block;

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
        }else if (object instanceof Blockierung){
            Blockierung block = (Blockierung)object;
            app.handler.root.blockierungen.put(block.id, block);
            app.handler.writeFile();
            app.server.sendToAllTCP(block);
        }else if(object instanceof NetworkData.RemoveBlockRequest){
            removeBlock(((NetworkData.RemoveBlockRequest) object).id);
            app.server.sendToAllTCP(object);
        }
    }
    
    private void addBlock(NetworkData.BlockRequest request){
        Blockierung blockierung = new Blockierung();
        blockierung.duration = request.duration;
        blockierung.start = request.start;
        blockierung.id = DataHandler.nextId(app.handler.root.blockierungen);
        blockierung.friseur = request.friseur;
        blockierung.msg = request.msg;

        app.handler.root.blockierungen.put(blockierung.id, blockierung);
        app.handler.writeFile();

        app.server.sendToAllTCP(blockierung);
    }
    
    private void removeBlock(int id){
        app.handler.root.blockierungen.remove(id);
        app.handler.writeFile();
    }
}
