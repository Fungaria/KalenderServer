/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer.server;

import calendarServer.database.Termin;
import calendarServer.database.Customer;
import calendarServer.database.Blockierung;
import calendarServer.database.Service;
import calendarServer.database.Vacation;
import calendarServer.database.DataRoot;
import calendarServer.database.Friseur;
import calendarServer.database.ServiceCategory;
import calendarServer.database.serializers.DateSerializer;
import calendarServer.database.serializers.DateTimeSerializer;
import calendarServer.server.listeners.LoginListener;
import calendarServer.server.listeners.TerminListener;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import calendarServer.server.NetworkData.*;
import calendarServer.server.listeners.BlockListener;
import calendarServer.server.listeners.ServiceListener;
import calendarServer.server.listeners.VacationListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

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

        k.register(ArrayList.class);
        k.register(HashMap.class);
        k.register(HashMap.Entry.class);
        k.register(DataRoot.class);
        k.register(LocalDate.class, new DateSerializer());
        k.register(LocalTime.class);
        k.register(LocalDateTime.class, new DateTimeSerializer());
        k.register(Customer.class);
        k.register(Friseur.class);
        k.register(Vacation.class);
        k.register(Service.class);
        k.register(ServiceCategory.class);
        k.register(Termin.class);
        k.register(Blockierung.class);
        k.register(LoginRequest.class);
        k.register(LoginResponse.class);
        k.register(TerminRequest.class);
        k.register(StornoRequest.class);
        k.register(BlockRequest.class);
        k.register(EditVacationRequest.class);
        k.register(RemoveVacationRequest.class);
        k.register(CreateVacationRequest.class);
        k.register(RemoveBlockRequest.class);
        k.register(CreateServiceRequest.class);
        k.register(RemoveServiceRequest.class);
    }

    private void registerListeners() {
        super.addListener(new TerminListener());
        super.addListener(new LoginListener());
        super.addListener(new BlockListener());
        super.addListener(new VacationListener());
        super.addListener(new ServiceListener());
    }
}
