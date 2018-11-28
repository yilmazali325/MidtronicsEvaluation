package tech.aliyilmaz.android.midtronicsevaluation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import tech.aliyilmaz.android.midtronicsevaluation.Interface.RetrofitArrayAPI;
import tech.aliyilmaz.android.midtronicsevaluation.Model.Country;
import tech.aliyilmaz.android.midtronicsevaluation.R;

public class CountryDetailActivity extends AppCompatActivity {
    private String url = "https://restcountries.eu/rest/v1/name/";
    private TextView capital, region, area, population, subregion, name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);


        capital = (TextView) findViewById(R.id.textCapital);
        area = (TextView) findViewById(R.id.textArea);
        region = (TextView) findViewById(R.id.textRegion);
        population = (TextView) findViewById(R.id.textPopulation);
        subregion = (TextView) findViewById(R.id.textSubregion);
        name = (TextView) findViewById(R.id.textCountryName);

        getRetrofitArray();

    }

    void getRetrofitArray() {

        // Get position of country from recyclerview which will be integer value
        Intent intent = getIntent();
        int data = intent.getIntExtra("countryPosition", 1000);

        // Get appropriate country name according to its position
        String countryName = getResources().getStringArray(R.array.countries_array)[data];

        // Create the Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Recognize the service which is an Interface that makes a GET request
        RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);

        // Make a Call to get the detail of the Country
        Call<List<Country>> call = service.getCountryDetails(countryName);

        // Do an asyncronous call
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Response<List<Country>> response, Retrofit retrofit) {

                try {
                    // Load the data inside the response body and put them into the appropriate TextViews
                    List<Country> countryData = response.body();
                    Log.d("data", response.body().toString());
                    for (int i = 0; i < countryData.size(); i++) {

                        if (i == 0) {
                            name.setText(countryData.get(i).getName());
                            area.setText(countryData.get(i).getArea());
                            capital.setText(countryData.get(i).getCapital());
                            region.setText(countryData.get(i).getRegion());
                            subregion.setText(countryData.get(i).getSubregion());
                            population.setText(countryData.get(i).getPopulation());
                        }
                    }
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
