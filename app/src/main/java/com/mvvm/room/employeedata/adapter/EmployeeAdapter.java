package com.mvvm.room.employeedata.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.mvvm.room.employeedata.R;
import com.mvvm.room.employeedata.model.EmployeeResponse;
import com.mvvm.room.employeedata.view.SecondActivity;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    public Context context;
    public ArrayList<EmployeeResponse.EmployeeData> empData;

    public EmployeeAdapter(Context context, ArrayList<EmployeeResponse.EmployeeData> empData) {
        this.context = context;
        this.empData = empData;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_empdata, parent, false);
        return new EmployeeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {

        EmployeeResponse.EmployeeData data = empData.get(position);

        if (data.id == null) {
            holder.lblEmpId.setText("- - -");
        }
        else {
            holder.lblEmpId.setText(data.id);
        }

        if (data.employee_name == null) {
            holder.lblEmpName.setText("- - -");
        }
        else {
            holder.lblEmpName.setText(data.employee_name);
        }

        holder.btnNextPage.setOnClickListener( v -> {

            Intent i = new Intent(context, SecondActivity.class);
            i.putExtra("id", data.id);
            i.putExtra("name", data.employee_name);
            i.putExtra("age", data.employee_age);
            i.putExtra("salary", data.employee_salary);
            i.putExtra("profilePicture", data.profile_image);
            ((AppCompatActivity) context).startActivity(i);
            ((AppCompatActivity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return empData.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView lblEmpId, lblEmpName;
        AppCompatButton btnNextPage;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            lblEmpId = itemView.findViewById(R.id.lblEmployeeIdAnswer);
            lblEmpName = itemView.findViewById(R.id.lblEmployeeNameAnswer);

            btnNextPage = itemView.findViewById(R.id.btnNextPage);
        }
    }
}
