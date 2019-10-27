package calendarServer.server.listeners;

import static calendarServer.Main.app;
import calendarServer.database.DataHandler;
import calendarServer.database.Service;
import calendarServer.server.NetworkData;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 *
 * @author sreis
 */
public class ServiceListener extends Listener{
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof NetworkData.CreateServiceRequest){
            NetworkData.CreateServiceRequest request = (NetworkData.CreateServiceRequest)object;
            Service service = new Service();
            service.id = DataHandler.nextId(app.handler.root.services);
            service.category = request.category;
            service.duration = request.duration;
            service.einwirkZeit = request.einwirkZeit;
            service.name = request.name;
            service.price = request.price;
            
            app.handler.root.services.put(service.id, service);
            app.handler.writeFile();
            app.server.sendToAllTCP(service);
        }else if(object instanceof Service){
            app.handler.root.services.put(((Service) object).id, (Service)object);
            app.handler.writeFile();
            app.server.sendToAllTCP((Service) object);
            
        }else if(object instanceof NetworkData.RemoveServiceRequest){
            
        }
    }
}
