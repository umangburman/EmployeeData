package com.mvvm.room.employeedata.model;

import java.util.ArrayList;

public class EmployeeResponse {

    public String status;
    public ArrayList<EmployeeData> data;

    public class EmployeeData {

        public String id;
        public String employee_name;
        public String employee_salary;
        public String employee_age;
        public String profile_image;

    }
}


