package tech.aliyilmaz.android.midtronicsevaluation.Interface;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import tech.aliyilmaz.android.midtronicsevaluation.Model.Country;


public interface RetrofitArrayAPI {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of country.
    */
    @GET("https://restcountries.eu/rest/v1/name/{name}")
    Call<List<Country>> getCountryDetails(@Path("name") String name);

}
