package com.mvvm.room.employeedata.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mvvm.room.employeedata.R;
import com.mvvm.room.employeedata.adapter.EmployeeAdapter;
import com.mvvm.room.employeedata.adapter.EmployeeDBAdapter;
import com.mvvm.room.employeedata.databinding.ActivityMainBinding;
import com.mvvm.room.employeedata.model.EmployeeResponse;
import com.mvvm.room.employeedata.viewmodel.EmployeeDataViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;

    private ActivityMainBinding binding;

    private EmployeeDataViewModel empViewModel;

    private EmployeeAdapter adapter;
    private EmployeeDBAdapter adapterDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        context = MainActivity.this;

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context,
                RecyclerView.VERTICAL, false));
        binding.recyclerView.setHasFixedSize(true);

        empViewModel = ViewModelProviders.of(this).get(EmployeeDataViewModel.class);

        binding.wp7progressBar.showProgressBar();

        // Get Data From Server
        if (empViewModel.isNetworkAvailable(context)) {

            empViewModel.getEmpLiveData(context).observe(this, employeeResponse -> {

                binding.wp7progressBar.hideProgressBar();

                if (employeeResponse == null) {
                    Snackbar.make(binding.home, getString(R.string.no_data), Snackbar.LENGTH_LONG).show();
                }
                else {

                    ArrayList<EmployeeResponse.EmployeeData> employeeData = employeeResponse.data;

                    adapter = new EmployeeAdapter(context, employeeData);
                    binding.recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            });

        }
        // Get Details from DB
        else {

            binding.wp7progressBar.hideProgressBar();

            empViewModel.getEmpLiveDataFromDB(context).observe(this, employeeTableResponse -> {

                if (employeeTableResponse.size() == 0) {
                    Snackbar.make(binding.home, getString(R.string.no_data), Snackbar.LENGTH_LONG).show();
                }
                else {

                    adapterDB = new EmployeeDBAdapter(context, employeeTableResponse);
                    binding.recyclerView.setAdapter(adapterDB);
                    adapterDB.notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}