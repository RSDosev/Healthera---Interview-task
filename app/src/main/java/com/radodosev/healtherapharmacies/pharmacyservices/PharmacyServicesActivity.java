package com.radodosev.healtherapharmacies.pharmacyservices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviActivity;
import com.radodosev.healtherapharmacies.R;
import com.radodosev.healtherapharmacies.data.model.PharmacyService;
import com.radodosev.healtherapharmacies.di.DI;
import com.radodosev.healtherapharmacies.pharmacyservices.adapter.PharmacyServicesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

import static com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesViewState.State.DATA_LOADED;
import static com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesViewState.State.ERROR;
import static com.radodosev.healtherapharmacies.pharmacyservices.PharmacyServicesViewState.State.LOADING;


public class PharmacyServicesActivity extends MviActivity<PharmacyServicesView, PharmacyServicesPresenter>
        implements PharmacyServicesView {
    private static final String EXTRA_PHARMACY_ID = "EXTRA_PHARMACY_ID";

    // ----- Instance fields -----
    @BindView(R.id.view_loading)
    View loadingView;
    @BindView(R.id.list_view_pharmacy_services)
    ListView servicesView;

    // ----- Activity lifecycle logic -----
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_details);
        ButterKnife.bind(this);
    }

    // ----- Creating the presenter -----
    @NonNull
    @Override
    public PharmacyServicesPresenter createPresenter() {
        return DI.providePharmacyDetailsPresenter();
    }

    // ----- Exposing the view intents-----
    @Override
    public Observable<String> loadPharmacyDetails() {
        return Observable.just(getIntent().getStringExtra(EXTRA_PHARMACY_ID));
    }

    // ----- Rendering the view state -----
    @Override
    public void render(PharmacyServicesViewState viewState) {
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
        servicesView.setVisibility(View.GONE);
    }

    private void renderDataLoaded(final List<PharmacyService> data) {
        loadingView.setVisibility(View.GONE);
        servicesView.setVisibility(View.VISIBLE);

        servicesView.setAdapter(new PharmacyServicesAdapter(this, data));
    }

    private void renderError(final Throwable error) {
        loadingView.setVisibility(View.GONE);

        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    public static void start(final Context context, final String pharmacyId) {
        final Intent intent = new Intent(context, PharmacyServicesActivity.class);
        intent.putExtra(EXTRA_PHARMACY_ID, pharmacyId);
        context.startActivity(intent);
    }
}
