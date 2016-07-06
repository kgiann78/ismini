
package gr.uoa.ec.ismini.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Customer implements KvmSerializable {

    protected Integer key;
    private Address addressKey;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(Address addressKey, Integer key, String firstName, String lastName, String username, String password) {
        this.addressKey = addressKey;
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
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
                return firstName;
            case 3:
                return lastName;
            case 4:
                return username;
            case 5:
                return password;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 6;
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
                firstName = value.toString();
                break;
            case 3:
                lastName = value.toString();
                break;
            case 4:
                username = value.toString();
            case 5:
                password = value.toString();
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
                info.name = "firstName";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "lastName";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "username";
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "password";
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "key=" + key +
                ", addressKey=" + addressKey +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
