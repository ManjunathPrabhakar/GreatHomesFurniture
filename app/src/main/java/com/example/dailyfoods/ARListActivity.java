package com.example.dailyfoods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ARListActivity extends AppCompatActivity {

    private static String spinnerData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arlist);

        setupSpinner();


    }


    private void setupSpinner() {
        List<String> list = new ArrayList<>();
        list.add("all");
        list.add("chairs");
        list.add("tables");
        list.add("desks");
        list.add("random");

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerData = list.get(i);
                Log.d("SPINITEM", spinnerData);
                recycleShow();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void recycleShow() {
        if (spinnerData.equals("")) {
            Toast.makeText(getApplicationContext(), "Error..", Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, Integer> mdata = getListofAll(spinnerData);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ListAdapter adapter = new ListAdapter(mdata);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private Map<String, Integer> getListofAll(String spinnerData) {
        Map<String, Integer> val;

        switch (spinnerData) {
            case "all":
                val = dataComponent.getAlllist();
                break;

            case "chairs":
                val = dataComponent.getChairlist();
                break;

            case "desks":
                val = dataComponent.getDesklist();
                break;

            case "tables":
                val = dataComponent.getTablelist();
                break;

            case "random":
                val = dataComponent.getRandomlist();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + spinnerData);
        }

        return val;
    }
}
