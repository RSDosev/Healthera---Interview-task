package com.radodosev.healtherapharmacies.di;

import com.radodosev.healtherapharmacies.data.remote.PharmaciesRemoteDataSource;
import com.radodosev.healtherapharmacies.listpharmacies.ListPharmaciesPresenter;
import com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesPresenter;

/**
 * Created by Rado on 8/24/2017.
 */

public final class DI {
    private DI() {
    }

    public static ListPharmaciesPresenter provideListPharmaciesPresenter() {
        return new ListPharmaciesPresenter(provideRemoteDataSource());
    }

    public static PharmacyServicesPresenter providePharmacyDetailsPresenter() {
        return new PharmacyServicesPresenter(provideRemoteDataSource());
    }

    public static PharmaciesRemoteDataSource provideRemoteDataSource(){
        return PharmaciesRemoteDataSource.get();
    }
}
