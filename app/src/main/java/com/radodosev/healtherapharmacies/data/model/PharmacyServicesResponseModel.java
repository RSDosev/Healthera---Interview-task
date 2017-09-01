package com.radodosev.healtherapharmacies.data.model;

import java.io.Serializable;

/**
 * Created by blue on 1.9.2017 г..
 */

public class PharmacyServicesResponseModel implements Serializable {
    private static final long serialVersionUID = -8777673813512564361L;

    private PharmacyService[] data;

    public PharmacyServicesResponseModel(PharmacyService[] data) {
        this.data = data;
    }

    public PharmacyService[] getData() {
        return data;
    }

    public void setData(PharmacyService[] data) {
        this.data = data;
    }
}
