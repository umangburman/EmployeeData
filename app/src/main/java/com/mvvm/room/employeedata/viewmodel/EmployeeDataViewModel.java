package com.mvvm.room.employeedata.viewmodel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mvvm.room.employeedata.model.EmployeeResponse;
import com.mvvm.room.employeedata.repository.EmployeeRepository;
import com.mvvm.room.employeedata.room.EmployeeTable;

import java.util.List;

public class EmployeeDataViewModel extends ViewModel {

    MutableLiveData<EmployeeResponse> empLiveData;
    MutableLiveData<List<EmployeeTable>> empLiveDataDB;

    public MutableLiveData<EmployeeResponse> getEmpLiveData(Context context) {

        EmployeeRepository repository = new EmployeeRepository();

        empLiveData = repository.getEmpApiCall(context);

        return empLiveData;
    }

    public MutableLiveData<List<EmployeeTable>> getEmpLiveDataFromDB(Context context) {

        EmployeeRepository repository = new EmployeeRepository();

        empLiveDataDB = repository.getEmpFromDB(context);

        return empLiveDataDB;
    }

    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
