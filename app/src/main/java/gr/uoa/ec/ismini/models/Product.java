
package gr.uoa.ec.ismini.models;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Product {

    protected String name;
    private String description;
    protected Integer key;
    private Category categoryKey;
    private double price;
    private double preparation;

    public Product() {
    }

    public Product(String name, String description, Integer key, Category categoryKey, double price, double preparation) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.categoryKey = categoryKey;
        this.price = price;
        this.preparation = preparation;
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

    public Category getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(Category categoryKey) {
        this.categoryKey = categoryKey;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPreparation() {
        return preparation;
    }

    public void setPreparation(double preparation) {
        this.preparation = preparation;
    }

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

    public int getPropertyCount() {
        return 2;
    }

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
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", key=" + key +
                ", categoryKey=" + categoryKey +
                ", price=" + price +
                ", preparation=" + preparation +
                '}';
    }
}
