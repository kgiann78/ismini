
package gr.uoa.ec.ismini.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Category implements KvmSerializable {

    private String description;
    protected Integer key;
    protected String name;

    public Category() {
    }

    public Category(String description, Integer key, String name) {
        this.description = description;
        this.key = key;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "{" +
                "description='" + description + '\'' +
                ", key=" + key +
                ", name='" + name + '\'' +
                '}';
    }
}
