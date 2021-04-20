package com.example.carshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    Button Register,Login;
    EditText txtEmail,txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Register=findViewById(R.id.Register);
        Login=findViewById(R.id.button_login);
        txtEmail=findViewById(R.id.loginEmail);
        txtPass=findViewById(R.id.Password);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
                finish();
            }
        }); 
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectDatabase db = new ConnectDatabase();
                java.sql.Connection con = db.connectDB();
                if (con == null) {
                    Toast.makeText(getApplication(), "Please Check Internet Access", Toast.LENGTH_LONG).show();
                }
                if (txtEmail.getText().toString().isEmpty()) {
                    txtEmail.setError("please Enter UserName");
                    txtEmail.requestFocus();
                } else if (txtPass.getText().toString().isEmpty()) {
                    txtPass.setError("please Enter PassWord");
                    txtPass.requestFocus();
                    txtPass.setError("Please Enter PassWord");
                }else{
                    try {
                        Statement stmt = con.createStatement();

                        ResultSet n = stmt.executeQuery("select * from Usertable where Email='" + txtEmail.getText().toString() + "' and Password='" + txtPass.getText().toString() + "'");

                        if (n.next() == true) {
                         Intent i=new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("UserId",n.getString(1));
                            i.putExtra("UserName","Mohamed Yehia");
                            i.putExtra("UserEmail","yehiahelmy754@gmail.com");
                            startActivity(i);
                            finish();
                           /* if(RememberMe.isChecked()) {
                                getSharedPreferences("MyLogin",MODE_PRIVATE).edit()
                                        .putString("UserName",txtUser.getText().toString())
                                        .putString("PassWord",txtPass.getText().toString())
                                        .commit();
                            }*/

                       } else {
                            txtEmail.setText("");
                            txtPass.setText("");
                            Toast.makeText(getApplication(), "UserName or PassWord is Not Valid", Toast.LENGTH_LONG).show();

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }}
            }
        });
    }
}