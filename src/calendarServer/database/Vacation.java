/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendarServer.database;

import calendarServer.database.adapters.DateAdapter;
import calendarServer.database.adapters.MapElement;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author sreis
 */
@XmlRootElement(name = "vacation")
public class Vacation extends MapElement{
    @XmlAttribute
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date start;
    @XmlAttribute
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date end;

    public Vacation() {
    }

    public Vacation(int id, Date start, Date end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return id+"-  start:"+start+"   end:"+end;
    }
}
