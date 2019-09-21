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
@XmlRootElement(name = "friseur")
public class Friseur {
    public int id;
    public String name;
    //TODO: services
}
