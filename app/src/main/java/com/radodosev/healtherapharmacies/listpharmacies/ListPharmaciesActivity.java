package com.radodosev.healtherapharmacies.listpharmacies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviActivity;
import com.radodosev.healtherapharmacies.R;
import com.radodosev.healtherapharmacies.data.model.PharmaciesResponseModel;
import com.radodosev.healtherapharmacies.data.model.Pharmacy;
import com.radodosev.healtherapharmacies.di.DI;
import com.radodosev.healtherapharmacies.listpharmacies.adapter.PharmaciesAdapter;
import com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

import static com.radodosev.healtherapharmacies.listpharmacies.ListPharmaciesViewState.State.DATA_LOADED;
import static com.radodosev.healtherapharmacies.listpharmacies.ListPharmaciesViewState.State.ERROR;
import static com.radodosev.healtherapharmacies.listpharmacies.ListPharmaciesViewState.State.LOADING;

public class ListPharmaciesActivity extends MviActivity<ListPharmaciesView, ListPharmaciesPresenter>
        implements ListPharmaciesView {
    // ----- Instance fields -----
    @BindView(R.id.list_view_pharmacies)
    ListView pharmaciesView;
    @BindView(R.id.view_loading)
    View loadingView;

    // ----- Activity lifecycle logic -----
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacies_list);
        ButterKnife.bind(this);
    }

    // ----- Creating the presenter -----
    @NonNull
    @Override
    public ListPharmaciesPresenter createPresenter() {
        return DI.provideListPharmaciesPresenter();
    }

    // ----- Exposing the view intents-----
    @Override
    public Observable<Boolean> loadPharmaciesNearby() {
        return Observable.just(true);
    }


    // ----- Rendering the view state -----
    @Override
    public void render(ListPharmaciesViewState viewState) {
        switch (viewState.getState()) {
            case LOADING:
                renderLoading();
                break;
            case DATA_LOADED:
                renderDataLoaded(viewState.getData());
                break;
            case ERROR:
                renderError(viewState.getError());
                break;
        }
    }

    private void renderLoading() {
        loadingView.setVisibility(View.VISIBLE);
        pharmaciesView.setVisibility(View.GONE);
    }

    private void renderDataLoaded(final PharmaciesResponseModel data) {
        loadingView.setVisibility(View.GONE);
        pharmaciesView.setVisibility(View.VISIBLE);

        pharmaciesView.setAdapter(new PharmaciesAdapter(this, Arrays.asList(data.getData()), pharmacy -> showPharmacyServices(pharmacy.getPlaceId())));
    }

    private void showPharmacyServices(final String pharmacyId) {
        PharmacyServicesActivity.start(this, pharmacyId);
    }


    private void renderError(final Throwable error) {
        loadingView.setVisibility(View.GONE);

        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }
}
