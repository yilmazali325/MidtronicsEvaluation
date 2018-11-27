package tech.aliyilmaz.android.midtronicsevaluation.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tech.aliyilmaz.android.midtronicsevaluation.Adapter.MyAdapter;
import tech.aliyilmaz.android.midtronicsevaluation.Model.Country;
import tech.aliyilmaz.android.midtronicsevaluation.R;

import static android.widget.LinearLayout.VERTICAL;

public class CountriesActivity extends AppCompatActivity {
    private List<Country> countryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MyAdapter(countryList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(mAdapter);

        prepareCountryData();
    }

    public void prepareCountryData() {
        String[] countryArr = getResources().getStringArray(R.array.countries_array);
        for (String s : countryArr) {
            countryList.add(new Country(s));
        }
        mAdapter.notifyDataSetChanged();
    }
}
