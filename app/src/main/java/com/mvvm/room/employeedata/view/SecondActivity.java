package com.mvvm.room.employeedata.view;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mvvm.room.employeedata.databinding.ActivitySecondBinding;
import com.mvvm.room.employeedata.services.CountTime;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    private Context context;

    private ActivitySecondBinding binding;

    private Intent i;

    private String id, name, age, salary, profilePic;

    public static String broadcastAction = "com.mvvm.room.employeedata.view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        context = SecondActivity.this;

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        i = getIntent();
        id = i.getStringExtra("id");
        name = i.getStringExtra("name");
        age = i.getStringExtra("age");
        salary = i.getStringExtra("salary");
        profilePic = i.getStringExtra("profilePicture");

        binding.lblEmployeeIdAnswer.setText(id);
        binding.lblEmployeeNameAnswer.setText(name);
        binding.lblEmployeeAgeAnswer.setText(age);
        binding.lblEmployeeSalaryAnswer.setText(salary);

        Intent intent = new Intent(SecondActivity.this, CountTime.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(broadcastAction);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
        Intent intent = new Intent(SecondActivity.this, CountTime.class);
        stopService(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
        finish();
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("Service", "Service Connected");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Service", "Service Disconnected");
        }
    };

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            binding.lblTimer.setText(intent.getStringExtra("count"));
            if (Objects.requireNonNull(intent.getStringExtra("count")).contentEquals("0")) {
                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                finish();
            }
        }
    };
}