package com.radodosev.healtherapharmacies.pharmacyservices;

import android.support.annotation.IntDef;

import com.radodosev.healtherapharmacies.data.model.PharmacyService;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesViewState.State.DATA_LOADED;
import static com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesViewState.State.ERROR;
import static com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesViewState.State.LOADING;


/**
 * Created by Rado on 7/8/2017.
 */

public class PharmacyServicesViewState {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LOADING, DATA_LOADED, ERROR})
    public @interface State {
        int LOADING = 1;
        int DATA_LOADED = 2;
        int ERROR = 3;
    }

    private final
    @State
    int state;
    private final Throwable error;
    private final List<PharmacyService> data;

    PharmacyServicesViewState(final @State int state,
                              final Throwable error,
                              final List<PharmacyService> data) {
        this.state = state;
        this.error = error;
        this.data = data;
    }

    public
    @State
    int getState() {
        return state;
    }

    public Throwable getError() {
        return error;
    }

    public List<PharmacyService> getData() {
        return data;
    }

    public static PharmacyServicesViewState ERROR(Throwable error) {
        return new PharmacyServicesViewState(ERROR, error, null);
    }

    public static PharmacyServicesViewState LOADING() {
        return new PharmacyServicesViewState(LOADING, null, null);
    }

    public static PharmacyServicesViewState DATA_LOADED(List<PharmacyService> data) {
        return new PharmacyServicesViewState(DATA_LOADED, null, data);
    }
}
