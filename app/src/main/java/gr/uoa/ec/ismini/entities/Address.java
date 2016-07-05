
package gr.uoa.ec.ismini.entities;
import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Address implements KvmSerializable {

    protected String description;
    protected Integer key;

    public Address() {
    }

    public Address(String description, Integer key) {
        this.description = description;
        this.key = key;
    }

    @Override
    public Object getProperty(int index) {
        switch(index)
        {
            case 0:
                return key;
            case 1:
                return description;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch(index)
        {
            case 0:
                key = Integer.parseInt(value.toString());
                break;
            case 1:
                description = value.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch(index)
        {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "key";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "description";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "description='" + description + '\'' +
                ", key=" + key +
                '}';
    }
}
