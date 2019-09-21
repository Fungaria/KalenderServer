/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import calendarServer.database.*;
import calendarServer.server.NetworkData.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sreis
 */
public class KalenderServer extends Server {

    private final int TIMEOUT = 5000;
    private final int TCP_PORT = 54777;
    private final int UDP_PORT = 54779;

    public KalenderServer() {
        registerClasses();
        registerListeners();
    }

    public void startServer() {
        super.start();
        try {
            super.bind(TCP_PORT, UDP_PORT);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void registerClasses() {
        Kryo k = getKryo();

        k.register(DataRoot.class);
        k.register(Kunde.class);
        k.register(Date.class);
        k.register(Termin.class);
        k.register(ArrayList.class);
        k.register(LoginRequest.class);
        k.register(LoginResponse.class);
        k.register(TerminRequest.class);
    }

    private void registerListeners() {
        super.addListener(new TerminListener());
        super.addListener(new LoginListener());
    }
}