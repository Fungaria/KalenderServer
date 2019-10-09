package calendarServer.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author sreis
 */
public class DataHandler {

    private static final String XML_LOCATION = "files\\data.xml";

    public DataRoot root;
    private JAXBContext context;

    public void load() {
        try {
            context = JAXBContext.newInstance(DataRoot.class);
            Unmarshaller um = context.createUnmarshaller();
            root = (DataRoot) um.unmarshal(new FileReader(XML_LOCATION));
        } catch (JAXBException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeFile() {
        try {
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(root, new File(XML_LOCATION));
        } catch (JAXBException ex) {
            Logger.getLogger(DataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setData(DataRoot dataRoot) {
        System.out.println(dataRoot);
        this.root = dataRoot;
        writeFile();
    }

    public int nextTerminId() {
        int maxId = 0;
        for (Termin termin : root.appointments) {
            maxId = Math.max(termin.id, maxId);
        }
        return maxId + 1;
    }

    public int nextBlockId() {
        int maxId = 0;
        for (Blockierung blockierung : root.blockierungen) {
            maxId = Math.max(blockierung.id, maxId);
        }
        return maxId + 1;
    }
}
