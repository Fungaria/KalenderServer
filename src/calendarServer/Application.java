/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer;

import calendarServer.database.DataHandler;
import calendarServer.server.KalenderServer;

/**
 *
 * @author sreis
 */
public class Application {
    
    public final KalenderServer server;
    public final DataHandler handler;
    
    public Application() {
        server = new KalenderServer();
        handler = new DataHandler();
        
        handler.load();
    }
    
    public void start(){
        server.startServer();
    }
    
    public void update(float delta){
        
    }
}