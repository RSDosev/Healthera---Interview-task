package com.radodosev.healtherapharmacies.pharmacyservices;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.radodosev.healtherapharmacies.data.remote.PharmaciesRemoteDataSource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Rado on 8/24/2017.
 */

public class PharmacyServicesPresenter extends MviBasePresenter<PharmacyServicesView, PharmacyServicesViewState> {

    private final PharmaciesRemoteDataSource remoteDataSource;

    public PharmacyServicesPresenter(final PharmaciesRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    protected void bindIntents() {

        final Observable<PharmacyServicesViewState> loadPharmacyServices =
                intent(PharmacyServicesView::loadPharmacyDetails)
                        .doOnNext(ignore -> Timber.d("intent: PharmacyServicesView::loadPharmacyDetails"))
                        .flatMap(pharmacyId -> remoteDataSource.getPharmacyServices(pharmacyId)
                                .map(PharmacyServicesViewState::DATA_LOADED)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .startWith(PharmacyServicesViewState.LOADING())
                                .onErrorReturn(PharmacyServicesViewState::ERROR));

        subscribeViewState(loadPharmacyServices, PharmacyServicesView::render);
    }
}
