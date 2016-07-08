package gr.uoa.ec.ismini.util.resource;

import android.os.Bundle;

import java.util.HashMap;

import gr.uoa.ec.ismini.R;

/**
 *
 */
public class ResourceOptions {
    private ResourceFilter filter;
    private ResourceOperation operation;
    private ResourceType type;
    private HashMap<ResourceKeyParameter, String> parameters = new HashMap<>();

    public ResourceOptions(ResourceFilter filter, ResourceOperation operation, ResourceType type) {
        this.filter = filter;
        this.operation = operation;
        this.type = type;
    }

    public ResourceFilter getFilter() {
        return filter;
    }

    public void setFilter(ResourceFilter filter) {
        this.filter = filter;
    }

    public ResourceOperation getOperation() {
        return operation;
    }

    public void setOperation(ResourceOperation operation) {
        this.operation = operation;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getParameter(ResourceKeyParameter keyParameter){
        return parameters.get(keyParameter);
    }

    public void addParameter(ResourceKeyParameter keyParameter, String value){
        parameters.put(keyParameter, value);
    }

    public static ResourceOptions createOptionsByType(ResourceType type){
        return new ResourceOptions(ResourceFilter.all, ResourceOperation.findAll,type);
    }

    public static ResourceOptions convertBundleToOptions(int id){

        ResourceType type = null;
        switch (id){
            case R.id.nav_stores:
                type = ResourceType.store;
                break;
            case R.id.nav_products:
                type = ResourceType.product;
                break;
            case R.id.nav_profile:
            case R.id.nav_history:
            case R.id.nav_statistics:
            case R.id.nav_reviews:
                type = ResourceType.storereview;
                break;
            default:
                type = ResourceType.store;
        }
        return new ResourceOptions(ResourceFilter.all,ResourceOperation.findAll, type);
    }
}
