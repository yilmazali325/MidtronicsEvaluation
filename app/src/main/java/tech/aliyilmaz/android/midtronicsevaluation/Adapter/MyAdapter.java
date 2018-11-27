package tech.aliyilmaz.android.midtronicsevaluation.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tech.aliyilmaz.android.midtronicsevaluation.Activity.CountryDetailActivity;
import tech.aliyilmaz.android.midtronicsevaluation.Model.Country;
import tech.aliyilmaz.android.midtronicsevaluation.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Country> countryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView countryName;

        public MyViewHolder(View view) {
            super(view);
            countryName = (TextView) view.findViewById(R.id.name);

            // Item click listener of RecyclerView
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open another activity and send the position of country in the recyclerview
                    Intent intent = new Intent(v.getContext(), CountryDetailActivity.class);
                    intent.putExtra("countryPosition", getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }


    public MyAdapter(List<Country> countryList) {
        this.countryList = countryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.countryName.setText(country.getName());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}