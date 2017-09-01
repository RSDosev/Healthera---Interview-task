package com.radodosev.healtherapharmacies.data.model;

import java.io.Serializable;

/**
 * Created by Rado on 8/24/2017.
 */

public class PharmaciesResponseModel implements Serializable {
    private static final long serialVersionUID = 9166774833396672545L;

    private Pharmacy[] data;

    public PharmaciesResponseModel(){}

    public PharmaciesResponseModel(Pharmacy[] data) {
        this.data = data;
    }
    public Pharmacy[] getData() {
        return data;
    }

    public void setData(Pharmacy[] data) {
        this.data = data;
    }
}
