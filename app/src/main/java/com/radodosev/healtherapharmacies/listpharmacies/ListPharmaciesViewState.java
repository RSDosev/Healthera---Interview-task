package com.radodosev.healtherapharmacies.listpharmacies;

import android.support.annotation.IntDef;

import com.radodosev.healtherapharmacies.data.model.PharmaciesResponseModel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.radodosev.healtherapharmacies.listpharmacies.ListPharmaciesViewState.State.DATA_LOADED;
import static com.radodosev.healtherapharmacies.listpharmacies.ListPharmaciesViewState.State.ERROR;
import static com.radodosev.healtherapharmacies.listpharmacies.ListPharmaciesViewState.State.LOADING;

/**
 * Created by Rado on 7/8/2017.
 */

class ListPharmaciesViewState {
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
    private final PharmaciesResponseModel data;

    ListPharmaciesViewState(final @State int state,
                            final Throwable error,
                            final PharmaciesResponseModel data) {
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

    public PharmaciesResponseModel getData() {
        return data;
    }

    public static ListPharmaciesViewState ERROR(Throwable error) {
        return new ListPharmaciesViewState(ERROR, error, null);
    }

    public static ListPharmaciesViewState LOADING() {
        return new ListPharmaciesViewState(LOADING, null, null);
    }

    public static ListPharmaciesViewState DATA_LOADED(PharmaciesResponseModel data) {
        return new ListPharmaciesViewState(DATA_LOADED, null, data);
    }
}
