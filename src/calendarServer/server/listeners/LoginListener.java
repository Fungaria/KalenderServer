package calendarServer.server.listeners;

import static calendarServer.Main.app;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import calendarServer.server.NetworkData.LoginRequest;
import calendarServer.server.NetworkData.LoginResponse;

/**
 *
 * @author sreis
 */
public class LoginListener extends Listener{
    
    private final LoginResponse response = new LoginResponse();
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof LoginRequest){
            response.msg = "oh me boii";
            response.sucess = true;
            connection.sendTCP(response);
            
            connection.sendTCP(app.handler.root);
        }
    }
}
