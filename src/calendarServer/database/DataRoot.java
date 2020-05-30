package calendarServer.database;

import calendarServer.database.adapters.BlockAdapter;
import calendarServer.database.adapters.ClientAdapter;
import calendarServer.database.adapters.ServiceAdapter;
import calendarServer.database.adapters.ServiceCategoryAdapter;
import calendarServer.database.adapters.TerminAdapter;
import calendarServer.database.adapters.WorkerAdapter;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(namespace = "de.fungistudii.kalender")
public class DataRoot {

    @XmlElement(name = "termine")
    @XmlJavaTypeAdapter(TerminAdapter.class)
    public final HashMap<Integer, Termin> appointments = new HashMap<>();

    @XmlElement(name = "blockierungen")
    @XmlJavaTypeAdapter(BlockAdapter.class)
    public final HashMap<Integer, Blockierung> blockierungen = new HashMap<>();

    @XmlElement(name = "kunden")
    @XmlJavaTypeAdapter(ClientAdapter.class)
    public final HashMap<Integer, Customer> kunden = new HashMap<>();

    @XmlElement(name = "friseure")
    @XmlJavaTypeAdapter(WorkerAdapter.class)
    public final HashMap<Integer, Employee> friseure = new HashMap<>();

    @XmlElement(name = "services")
    @XmlJavaTypeAdapter(ServiceAdapter.class)
    public final HashMap<Integer, Service> services = new HashMap<>();

    @XmlElement(name = "serviceCategories")
    @XmlJavaTypeAdapter(ServiceCategoryAdapter.class)
    public final HashMap<Integer, ServiceCategory> serviceCategories = new HashMap<>();

    @Override
    public String toString() {
        return appointments.toString() + "\n" + kunden.toString();
    }
}
