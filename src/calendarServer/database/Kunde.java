/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer.database;

import calendarServer.database.adapters.MapElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sreis
 */
@XmlRootElement(name = "kunde")
public class Kunde extends MapElement{
    @XmlAttribute
    public String name;
    @XmlAttribute
    public String vorname;
    @XmlAttribute
    public String phone;

    @Override
    public String toString() {
        return vorname + " " + name;
    }
}
