package com.example.carshopping;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;
import java.sql.Statement;

public class AddUser extends AppCompatActivity {
    private String UserName, Email, PassWord;

    public AddUser(String UserName, String Email, String PassWord) {
        this.UserName = UserName;
        this.Email = Email;
        this.PassWord = PassWord;
    }

    public String Add() {
        ConnectDatabase db = new ConnectDatabase();
        java.sql.Connection con = db.connectDB();
        if (con == null) {
            Toast.makeText(getApplication(), "Please Check Internet Access", Toast.LENGTH_LONG).show();
        } else {
            try {
                Statement stmt = con.createStatement();
                String nul="empty";
                int x = stmt.executeUpdate("insert into Usertable values('" + UserName + "','" + Email + "','" + PassWord + "','" + nul + "')");
                //int x = stmt.executeUpdate("insert into Customers values(mohamed,mohamedAhmed,Shoubra,mohamed@gmail.com,01144831131,01144831131)");
                if (x == 0) {
                    return "Sorry Try again";
                } else {
                    return "User has been created succed";
                }
            } catch (SQLException e) {
                return "Sorry Error" + e.getMessage() + e.getErrorCode();
                //e.printStackTrace();
            }
        }
        return "error";
    }
}


