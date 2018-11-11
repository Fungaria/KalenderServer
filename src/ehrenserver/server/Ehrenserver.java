/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ehrenserver.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import ehrenserver.data.DataRoot;
import ehrenserver.data.Genosse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sreis
 */
public class Ehrenserver extends Server{
    
    private final int TIMEOUT = 5000;
    private final int TCP_PORT = 54777;
    private final int UDP_PORT = 54779;
    
    public Ehrenserver() {
        registerClasses();
        registerListeners();
    }
    
    public void startServer(){
        super.start();
        try {
            super.bind(TCP_PORT, UDP_PORT);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private void registerClasses(){
        Kryo k = getKryo();
        
        k.register(NetworkData.EhreRequest.class);
        k.register(DataRoot.class);
        k.register(Genosse.class);
        k.register(ArrayList.class);
    }
    
    private void registerListeners(){
        super.addListener(new EhrenListener());
    }
}
