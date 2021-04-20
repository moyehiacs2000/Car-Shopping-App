package com.example.carshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.Message.RecipientType.CC;
import static javax.mail.internet.InternetAddress.*;

public class RegisterActivity extends AppCompatActivity {
    Button Active,SignUp;
    LinearLayout mykonten,overBox;
    EditText code,email,name,password1,password2;
    ImageView power,close;
    Animation fromsmall,fromnothing,forActive,togo;
    Random r=new Random();
    int activationcode;
    String SEmail,sPassword,eto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Active=findViewById(R.id.Active);
        SignUp=findViewById(R.id.sign);
        mykonten=findViewById(R.id.myKonter);
        close=findViewById(R.id.imgclose);
        overBox=findViewById(R.id.overbox);
        power=(ImageView)findViewById(R.id.imgPower);
        fromsmall= AnimationUtils.loadAnimation(this,R.anim.fromsmall);
        fromnothing= AnimationUtils.loadAnimation(this,R.anim.fromnothing);
        forActive= AnimationUtils.loadAnimation(this,R.anim.fromactive);
        togo= AnimationUtils.loadAnimation(this,R.anim.togo);
        code=findViewById(R.id.code);
        email=findViewById(R.id.Email);
        name=findViewById(R.id.Name);
        password1=findViewById(R.id.Password1);
        password2=findViewById(R.id.Password2);
        Active.setVisibility(View.GONE);
        code.setVisibility(View.GONE);
        mykonten.setAlpha(0);
        overBox.setAlpha(0);
        power.setVisibility(View.GONE);
        close.setVisibility(View.GONE);
        SEmail="my5617057@gmail.com";
        sPassword="01005810079";
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password1.getText().toString().equals(password2.getText().toString())){
                    activationcode = r.nextInt((95684 + 12345 - 1) + 12345);
                    Properties properties=new Properties();
                    properties.put("mail.smtp.auth","true");
                    properties.put("mail.smtp.starttls.enable","true");
                    properties.put("mail.smtp.host","smtp.gmail.com");
                    properties.put("mail.smtp.port","587");
                    Session session=Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(SEmail,sPassword);
                        }
                    });
                    Message message=new MimeMessage(session);
                    try {
                        eto=email.getText().toString().trim();
                        message.setFrom(new InternetAddress(SEmail));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(eto));
                        message.setSubject("Cars Activation code.");
                        message.setText("Your Activation code "+activationcode);
                        new SendMail().execute(message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    password1.setText("");
                    password2.setText("");
                    Toast.makeText(getApplication(),"Two Password Not Identicle",Toast.LENGTH_LONG).show();
                }

            }

         });

        Active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(code.getText().toString())==activationcode){
                    AddUser newuser= new AddUser(name.getText().toString(),email.getText().toString(),password1.getText().toString());
                    String res=newuser.Add();
                    if(res.equals("User has been created succed")) {
                        overBox.startAnimation(togo);
                        mykonten.startAnimation(togo);
                        close.startAnimation(togo);
                        power.startAnimation(togo);
                        power.setVisibility(View.GONE);
                        close.setVisibility(View.GONE);
                        ViewCompat.animate(mykonten).setStartDelay(1000).alpha(0).start();
                        ViewCompat.animate(overBox).setStartDelay(1000).alpha(0).start();
                        Active.setVisibility(View.GONE);
                        code.setVisibility(View.GONE);
                        Toast.makeText(getApplication(),"You sign Up Successfully",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }
                    else{
                        Toast.makeText(getApplication(),res,Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    code.setText("");
                    code.setError("Wrong Code");
                    code.requestFocus();
                }

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overBox.startAnimation(togo);
                mykonten.startAnimation(togo);
                close.startAnimation(togo);
                power.startAnimation(togo);
                power.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
                ViewCompat.animate(mykonten).setStartDelay(1000).alpha(0).start();
                ViewCompat.animate(overBox).setStartDelay(1000).alpha(0).start();
                Active.setVisibility(View.GONE);
                code.setVisibility(View.GONE);
                email.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                password2.setVisibility(View.VISIBLE);
                password1.setVisibility(View.VISIBLE);
                SignUp.setVisibility(View.VISIBLE);
            }
        });
    }

    private class SendMail extends AsyncTask<Message,String,String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RegisterActivity.this
                    ,"Please Wait","Sending mail....",true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try{
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success")){
                power.setVisibility(View.VISIBLE);
                email.setVisibility(View.GONE);
                name.setVisibility(View.GONE);
                password2.setVisibility(View.GONE);
                password1.setVisibility(View.GONE);
                SignUp.setVisibility(View.GONE);
                power.startAnimation(forActive);
                overBox.setAlpha(1);
                overBox.startAnimation(fromnothing);
                mykonten.setAlpha(1);
                mykonten.startAnimation(fromsmall);
                close.setVisibility(View.VISIBLE);
                close.startAnimation(fromsmall);
                Active.setVisibility(View.VISIBLE);
                code.setVisibility(View.VISIBLE);
            }
            else{
                Toast.makeText(getApplicationContext(),"Some thing wrong",Toast.LENGTH_LONG).show();
            }
        }
    }
}