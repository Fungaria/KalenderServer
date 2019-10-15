/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer.database.adapters;

import calendarServer.database.Kunde;

/**
 *
 * @author sreis
 */
public class ClientAdapter extends MapAdapter<Kunde>{
    public ClientAdapter() {
        super(Kunde[]::new);
    }
}