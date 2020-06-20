package com.mvvm.room.employeedata.retrofit;

import com.mvvm.room.employeedata.model.EmployeeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("employees")
    Call<EmployeeResponse> getEmployeeList();

}
