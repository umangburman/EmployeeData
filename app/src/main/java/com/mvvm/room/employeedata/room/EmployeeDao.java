package com.mvvm.room.employeedata.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void insertDetails(EmployeeTable data);

    @Query("select * from EmployeeDetails")
    List<EmployeeTable> getDetails();

    @Query("delete from EmployeeDetails")
    void deleteAllData();

}
