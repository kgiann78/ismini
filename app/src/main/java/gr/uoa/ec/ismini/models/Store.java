
package gr.uoa.ec.ismini.models;
import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Store implements KvmSerializable {

    private Address addressKey;
    protected Integer key;
    protected String name;
    private float radius;

    public Store() {
    }

    public Store(Address addressKey, Integer key, String name, float radius) {
        this.addressKey = addressKey;
        this.key = key;
        this.name = name;
        this.radius = radius;
    }


    @Override
    public Object getProperty(int index) {
        switch(index)
        {
            case 0:
                return key;
            case 1:
                return addressKey;
            case 2:
                return name;
            case 3:
                return radius;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch(index)
        {
            case 0:
                key = Integer.parseInt(value.toString());
                break;
            case 1:
                addressKey = (Address) value;
                break;
            case 2:
                name = value.toString();
                break;
            case 3:
                radius = Float.parseFloat(value.toString());
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
                info.type = Address.class;
                info.name = "addressKey";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "name";
                break;
            case 3:
                info.type = Float.class;
                info.name = "radius";
                break;
            default:
                break;
        }
    }

    public Address getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(Address addressKey) {
        this.addressKey = addressKey;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "{" +
                "addressKey=" + addressKey +
                ", key=" + key +
                ", name=\'" + name + '\'' +
                ", radius=" + radius +
                '}';
    }
}
