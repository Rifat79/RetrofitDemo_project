package com.example.mdibrahim.retroexp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView, listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listViewHeroes);
        //listView2 = (ListView) findViewById(R.id.listViewHeroes2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero>heroes = response.body();


                String[] heroNames = new String[heroes.size()];
                String[] heroUrl = new String[heroes.size()];

                for (int i = 0; i < heroes.size(); i++) {
                    heroNames[i] = "Name : " + heroes.get(i).getLogin();
                    heroNames[i] += "\n\nUser_URL : " + heroes.get(i).getGistsUrl()+"\n";
                }

                listView.setAdapter(
                        new ArrayAdapter<String>(
                            getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                heroNames
                        )
                );

                /*listView2.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                heroUrl
                        )
                );*/

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}

