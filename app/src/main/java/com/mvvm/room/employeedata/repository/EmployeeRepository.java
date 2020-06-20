package com.mvvm.room.employeedata.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.mvvm.room.employeedata.model.EmployeeResponse;
import com.mvvm.room.employeedata.retrofit.ApiInterface;
import com.mvvm.room.employeedata.retrofit.RetrofitClient;
import com.mvvm.room.employeedata.room.EmployeeDao;
import com.mvvm.room.employeedata.room.EmployeeDatabase;
import com.mvvm.room.employeedata.room.EmployeeTable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {

    private MutableLiveData<EmployeeResponse> empLiveData;
    private MutableLiveData<List<EmployeeTable>> empLiveDataDB;

    private RetrofitClient retrofitClient;
    private ApiInterface apiInterface;

    private EmployeeDao empDao;
    private MutableLiveData<ArrayList<EmployeeTable>> allData;

    public MutableLiveData<EmployeeResponse> getEmpApiCall(Context context) {

        EmployeeDatabase db = EmployeeDatabase.getDatabase(context);
        empDao = db.empDoa();

        new DeleteEmployees(empDao).execute();

        retrofitClient = new RetrofitClient();

        apiInterface = retrofitClient.getRetrofitClient();

        empLiveData = new MutableLiveData<>();

        Call<EmployeeResponse> empCallResponse = apiInterface.getEmployeeList();

        empCallResponse.enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(@NotNull Call<EmployeeResponse> call, @NotNull Response<EmployeeResponse> response) {

                EmployeeResponse empResponse = response.body();

                if (empResponse != null) {

                    ArrayList<EmployeeResponse.EmployeeData> empData = empResponse.data;

                    for (EmployeeResponse.EmployeeData data : empData) {

                        EmployeeTable tableData = new EmployeeTable();
                        tableData.setId(data.id);
                        tableData.setName(data.employee_name);
                        tableData.setAge(data.employee_age);
                        tableData.setSalary(data.employee_salary);
                        tableData.setImage(data.profile_image);
                        new InsertEmployee(empDao).execute(tableData);
                    }

                }

                empLiveData.setValue(empResponse);

            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                empLiveData.setValue(null);
            }
        });

        return empLiveData;
    }

    // Getting Data from DB
    public MutableLiveData<List<EmployeeTable>> getEmpFromDB(Context context) {

        EmployeeDatabase db = EmployeeDatabase.getDatabase(context);
        empDao = db.empDoa();

        empLiveDataDB = new MutableLiveData<>();

        List<EmployeeTable> empTable = null;
        try {
            empTable = new GetEmployees(empDao).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        empLiveDataDB.setValue(empTable);

        return empLiveDataDB;
    }

    private static class InsertEmployee extends AsyncTask<EmployeeTable, Void, Void> {

        private EmployeeDao empDao;

        private InsertEmployee(EmployeeDao empDao) {
            this.empDao = empDao;
        }

        @Override
        protected Void doInBackground(EmployeeTable... data) {

            empDao.insertDetails(data[0]);
            return null;

        }

    }

    private static class DeleteEmployees extends AsyncTask<EmployeeTable, Void, Void> {

        private EmployeeDao empDao;

        private DeleteEmployees(EmployeeDao empDao) {
            this.empDao = empDao;
        }

        @Override
        protected Void doInBackground(EmployeeTable... data) {

            empDao.deleteAllData();
            return null;

        }

    }

    private static class GetEmployees extends AsyncTask<Void, Void, List<EmployeeTable>> {

        private EmployeeDao empDao;

        private GetEmployees(EmployeeDao empDao) {
            this.empDao = empDao;
        }

        @Override
        protected List<EmployeeTable> doInBackground(Void... voids) {
            return empDao.getDetails();
        }

    }

}

