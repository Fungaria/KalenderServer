package calendarServer.database;

import calendarServer.database.adapters.MapElement;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author sreis
 */
public class ServiceCategory extends MapElement{
    @XmlAttribute
    public String name;

    @Override
    public String toString() {
        return name;
    }
}
