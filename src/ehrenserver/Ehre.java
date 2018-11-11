/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ehrenserver;

import ehrenserver.server.Ehrenserver;
import ehrenserver.data.DataHandler;

/**
 *
 * @author sreis
 */
public class Ehre {
    
    public final Ehrenserver server;
    public final DataHandler handler;
    
    public Ehre() {
        server = new Ehrenserver();
        handler = new DataHandler();
        
        handler.load();
    }
    
    public void start(){
        server.startServer();
    }
    
    public void update(float delta){
        
    }
}