package com.radodosev.healtherapharmacies.data;

import com.radodosev.healtherapharmacies.data.model.PharmaciesResponseModel;
import com.radodosev.healtherapharmacies.data.model.PharmacyService;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Rado on 8/24/2017.
 */

public interface PharmaciesDataSource {
    Observable<PharmaciesResponseModel> getPharmaciesNearby(String latitude, String longitude);

    Observable<List<PharmacyService>> getPharmacyServices(String pharmacyId);
}
