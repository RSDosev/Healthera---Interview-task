package com.radodosev.healtherapharmacies.listpharmacies;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.radodosev.healtherapharmacies.data.remote.PharmaciesRemoteDataSource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Rado on 8/24/2017.
 */

public class ListPharmaciesPresenter extends MviBasePresenter<ListPharmaciesView, ListPharmaciesViewState> {

    private final PharmaciesRemoteDataSource remoteDataSource;

    public ListPharmaciesPresenter(final PharmaciesRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    protected void bindIntents() {

        final Observable<ListPharmaciesViewState> loadThePharmaciesNearby =
                intent(ListPharmaciesView::loadPharmaciesNearby)
                        .doOnNext(ignore -> Timber.d("intent: PharmacyServicesView::fetchPharmaciesNearby"))
                        .flatMap(ignore -> remoteDataSource.getPharmaciesNearby("51.657", "0.121")
                                .map(ListPharmaciesViewState::DATA_LOADED)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .startWith(ListPharmaciesViewState.LOADING())
                                .onErrorReturn(ListPharmaciesViewState::ERROR));

        subscribeViewState(loadThePharmaciesNearby, ListPharmaciesView::render);
    }
}
