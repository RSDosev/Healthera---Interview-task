package com.radodosev.healtherapharmacies.pharmacyservices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.radodosev.healtherapharmacies.R;
import com.radodosev.healtherapharmacies.data.model.Pharmacy;
import com.radodosev.healtherapharmacies.data.model.PharmacyService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rado on 8/25/2017.
 */

public class PharmacyServicesAdapter extends BaseAdapter {
    private final Context context;
    private final List<PharmacyService> data;

    public PharmacyServicesAdapter(final Context context, final List<PharmacyService> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PharmacyViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_pharmacy_service_item, null);
            holder = new PharmacyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PharmacyViewHolder) convertView.getTag();
        }

        holder.bind(data.get(position));
        return convertView;
    }


    public class PharmacyViewHolder {
        @BindView(R.id.image_view_logo)
        ImageView logoView;
        @BindView(R.id.text_view_title)
        TextView titleView;
        @BindView(R.id.text_view_description)
        TextView descriptionView;

        View rootView;

        public PharmacyViewHolder(final View rootView) {
            ButterKnife.bind(this, rootView);
            this.rootView = rootView;
        }

        void bind(final PharmacyService pharmacyService) {
            titleView.setText(pharmacyService.getServiceName());
            descriptionView.setText(pharmacyService.getServiceDescription());
            Glide.with(context).load(pharmacyService.getServiceIcon()).load(logoView);
        }
    }
}
