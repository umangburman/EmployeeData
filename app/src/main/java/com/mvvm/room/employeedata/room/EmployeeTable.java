package com.mvvm.room.employeedata.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "EmployeeDetails")
public class EmployeeTable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int dbId;

    @ColumnInfo(name = "EmpId")
    private String id;

    @ColumnInfo(name = "EmpName")
    private String Name;

    @ColumnInfo(name = "EmpAge")
    private String Age;

    @ColumnInfo(name = "EmpSalary")
    private String Salary;

    @ColumnInfo(name = "EmpImage")
    private String Image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }
}
