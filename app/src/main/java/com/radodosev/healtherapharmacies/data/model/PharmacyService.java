package com.radodosev.healtherapharmacies.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by blue on 1.9.2017 г..
 */

public class PharmacyService implements Serializable {
    private static final long serialVersionUID = -2680828700260099987L;

    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("service_enabled")
    private boolean serviceEnabled;
    @SerializedName("pharmacy_id")
    private String pharmacyId;
    @SerializedName("service_id")
    private String serviceId;
    @SerializedName("service_description")
    private String serviceDescription;
    @SerializedName("service_icon")
    private String serviceIcon;
    @SerializedName("service_price")
    private String servicePrice;
    @SerializedName("service_quantity")
    private String service_quantity;

    public PharmacyService(){

    }

    public PharmacyService(String serviceName, boolean serviceEnabled, String pharmacyId, String serviceId, String serviceDescription, String serviceIcon, String servicePrice, String service_quantity) {
        this.serviceName = serviceName;
        this.serviceEnabled = serviceEnabled;
        this.pharmacyId = pharmacyId;
        this.serviceId = serviceId;
        this.serviceDescription = serviceDescription;
        this.serviceIcon = serviceIcon;
        this.servicePrice = servicePrice;
        this.service_quantity = service_quantity;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isServiceEnabled() {
        return serviceEnabled;
    }

    public void setServiceEnabled(boolean serviceEnabled) {
        this.serviceEnabled = serviceEnabled;
    }

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceIcon() {
        return serviceIcon;
    }

    public void setServiceIcon(String serviceIcon) {
        this.serviceIcon = serviceIcon;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getService_quantity() {
        return service_quantity;
    }

    public void setService_quantity(String service_quantity) {
        this.service_quantity = service_quantity;
    }
}
