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
import com.mvvm.room.employeedata.room.EmployeeTable;
import com.mvvm.room.employeedata.view.SecondActivity;

import java.util.List;

public class EmployeeDBAdapter extends RecyclerView.Adapter<EmployeeDBAdapter.EmployeeDBViewHolder> {

    public Context context;
    public List<EmployeeTable> empData;

    public EmployeeDBAdapter(Context context, List<EmployeeTable> empData) {
        this.context = context;
        this.empData = empData;
    }

    @NonNull
    @Override
    public EmployeeDBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_empdata, parent, false);
        return new EmployeeDBViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeDBViewHolder holder, int position) {

        EmployeeTable data = empData.get(position);

        if (data.getId() == null) {
            holder.lblEmpId.setText("- - -");
        }
        else {
            holder.lblEmpId.setText(data.getId());
        }

        if (data.getName() == null) {
            holder.lblEmpName.setText("- - -");
        }
        else {
            holder.lblEmpName.setText(data.getName());
        }

        holder.btnNextPage.setOnClickListener( v -> {

            Intent i = new Intent(context, SecondActivity.class);
            i.putExtra("id", data.getId());
            i.putExtra("name", data.getName());
            i.putExtra("age", data.getAge());
            i.putExtra("salary", data.getSalary());
            i.putExtra("profile_picture", data.getImage());
            ((AppCompatActivity) context).startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return empData.size();
    }

    public class EmployeeDBViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView lblEmpId, lblEmpName;
        AppCompatButton btnNextPage;

        public EmployeeDBViewHolder(@NonNull View itemView) {
            super(itemView);

            lblEmpId = itemView.findViewById(R.id.lblEmployeeIdAnswer);
            lblEmpName = itemView.findViewById(R.id.lblEmployeeNameAnswer);

            btnNextPage = itemView.findViewById(R.id.btnNextPage);
        }
    }
}
