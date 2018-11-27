package tech.aliyilmaz.android.midtronicsevaluation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tech.aliyilmaz.android.midtronicsevaluation.Activity.CountriesActivity;

public class MainActivity extends AppCompatActivity {
    private Button countriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countriesButton = (Button) findViewById(R.id.countryButton);
        countriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CountriesActivity.class);
                startActivity(intent);
            }
        });

    }
}
