package com.mvvm.room.employeedata.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EmployeeTable.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {

    public abstract EmployeeDao empDoa();

    private static EmployeeDatabase INSTANCE;

    public static EmployeeDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (EmployeeDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder (
                            context, EmployeeDatabase.class, "EMPLOYEE_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();

                }

            }

        }

        return INSTANCE;

    }
}
