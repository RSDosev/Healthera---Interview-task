package com.radodosev.healtherapharmacies.listpharmacies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.radodosev.healtherapharmacies.R;
import com.radodosev.healtherapharmacies.data.model.Pharmacy;
import com.radodosev.healtherapharmacies.util.CommonUtils;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rado on 8/25/2017.
 */

public class PharmaciesAdapter extends BaseAdapter {
    private final Context context;
    private final List<Pharmacy> data;
    private final OnPharmacySelectedCallback onPharmacySelected;

    public PharmaciesAdapter(final Context context, final List<Pharmacy> data, OnPharmacySelectedCallback onPharmacySelected) {
        this.context = context;
        this.data = data;
        this.onPharmacySelected = onPharmacySelected;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_pharmacy_item, null);
            holder = new PharmacyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PharmacyViewHolder) convertView.getTag();
        }

        holder.bind(data.get(position), onPharmacySelected);
        return convertView;
    }


    public class PharmacyViewHolder {
        @BindView(R.id.text_view_title)
        TextView title;
        @BindView(R.id.text_view_type)
        TextView type;
        @BindView(R.id.text_view_distance)
        TextView distance;

        View rootView;

        public PharmacyViewHolder(final View rootView) {
            ButterKnife.bind(this, rootView);
            this.rootView = rootView;
        }

        void bind(final Pharmacy pharmacy, OnPharmacySelectedCallback onPharmacySelected) {
            title.setText(pharmacy.getPlaceTitle());
            type.setText(pharmacy.getPlaceType());
            distance.setText(pharmacy.getCurrentDistance());

            rootView.setOnClickListener(view -> onPharmacySelected.onPharmacySelected(pharmacy));
        }
    }

    public interface OnPharmacySelectedCallback{
        void onPharmacySelected(Pharmacy pharmacy);
    }
}
