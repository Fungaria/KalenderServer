/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer.database;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sreis
 */
@XmlRootElement(name = "kunde")
public class Kunde {
    public String name;
    public String vorname;
    public int id;
}
