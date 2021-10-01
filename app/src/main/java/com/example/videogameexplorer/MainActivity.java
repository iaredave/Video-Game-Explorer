package com.example.videogameexplorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lel";
    RecyclerView recyclerView;
    ProgressBar progressBar;
//    LinearLayoutManager layoutManager;
//    GamesAdapter adapter;
//    List<Games> gamesList = new ArrayList<>();
    ApiInterface apiInterface;
    private GamesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        //        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//        getCurrentDate();
        fetchGames();
    }

    public Context getContext() {
        return (Context)this;
    }

public String getCurrentDate() {
//    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    Date date = new Date();
//    String currentDate = dateFormat.format(date);
//
//    Log.v(TAG, "currentDate= " + currentDate);
//    return dateFormat.format(date);
//    LocalDate today = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//    LocalDate later = today.plusDays(5);
//    String formattedString = localDate.format(formatter);
    LocalDate localDate = LocalDate.now();//For reference
    LocalDate futureDate = localDate.plusDays(10);//For reference
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedLocalString = localDate.format(formatter);
    String formattedFutureString = futureDate.format(formatter);


//    Log.v(TAG, "currentDate= " + today);
//    Log.v(TAG, "laterDate= " + later);
    Log.v(TAG, "combined Date= " + formattedLocalString+"%2C"+formattedFutureString);
    return formattedLocalString+","+formattedFutureString;
}

    private void fetchGames() {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getRetrofitClient().getGames( "aa17288ae98d4b18a1fc1b34bfb44e01", getCurrentDate(), "-added").enqueue(new Callback<Games>() {
            @Override
            public void onResponse(Call<Games> call, Response<Games> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Results> resultsList = response.body().getResults();
                    for(int i = 0; i < resultsList.size(); i++) {
                        adapter = new GamesAdapter((ArrayList<Results>)resultsList, getContext());
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
//                    Collections.sort(gamesList, Games.LocationsNameComparator);
//                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Games> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sort_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.nameSort:
//                Collections.sort(gamesList, Games.LocationsNameComparator);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Sorted by name", Toast.LENGTH_SHORT).show();
                break;
            case R.id.distanceSort:
//                Collections.sort(locationsList, Locations.LocationsDistanceComparator);
//                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Sorted by distance", Toast.LENGTH_SHORT).show();
                break;
            case R.id.arrivalSort:
//                Collections.sort(gamesList, Games.LocationsArrivalTimeComparator);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Sorted by arrival time", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}