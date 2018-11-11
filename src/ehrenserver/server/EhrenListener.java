package ehrenserver.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import ehrenserver.Main;
import ehrenserver.data.DataHandler;
import ehrenserver.data.DataRoot;
import ehrenserver.data.Genosse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author sreis
 */
public class EhrenListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof NetworkData.EhreRequest) {
            NetworkData.EhreRequest reqest = (NetworkData.EhreRequest) object;

            Genosse genosse = Main.ehre.handler.getGenosse(reqest.id);
            genosse.ehre += reqest.ehreChange;

            try {
                Main.ehre.handler.writeFile();
            } catch (JAXBException ex) {
                Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Main.ehre.server.sendToAllTCP(Main.ehre.handler.root);
        }
    }
}
