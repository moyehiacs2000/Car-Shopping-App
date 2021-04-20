package com.example.carshopping;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class recomend {
    private String UserID;
    private String CarID;
    private Context context;
    Set<String> MatchUser,allfav ;
    //private ArrayList<String>MatchUser;
    private ArrayList<String>RcomendItem;
    private ArrayList<String> getRecomendedCarIDS;
    public recomend(String userID, String carID,Context context) {
        UserID = userID;
        CarID = carID;
        //MatchUser=new ArrayList<String>();
        RcomendItem=new ArrayList<String>();
        MatchUser = new HashSet<>();
        allfav = new HashSet<>();
        this.context=context;
        getMAtchUser(CarID);
        getRecomendedList();
        insertintoDataBase();

    }
    public void setGetRecomendedCarIds() {
        getRecomendedCarIDS=new ArrayList<String>();
        ConnectDatabase dp=new ConnectDatabase();
        Connection conn = dp.connectDB();
        try {
            Statement stmt = conn.createStatement();
            ResultSet R = stmt.executeQuery("select * from Recomended where UserID = '" + UserID + "'");
            R.next();
            do {
                if (UserID != R.getString(1)) {
                    getRecomendedCarIDS.add(R.getString(2));
                }

            } while (R.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getUserFav(){
        ConnectDatabase dp=new ConnectDatabase();
        Connection conn=dp.connectDB();
        try {
            Statement stmt=conn.createStatement();
            ResultSet R=stmt.executeQuery("select * from Favorits where UserID = '"+UserID+"'");
            R.next();
            do{
                allfav.add(R.getString(2));

            }while (R.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getMAtchUser(String Car){
        ConnectDatabase dp=new ConnectDatabase();
        Connection conn=dp.connectDB();
        try {
            Statement stmt=conn.createStatement();
            ResultSet R=stmt.executeQuery("select * from Favorits where CarID = '"+CarID+"'");
            R.next();
            do{
                if(!UserID.equals(R.getString(1))){
                    MatchUser.add(R.getString(1));

                }

            }while (R.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getRecomendedList(){
        setGetRecomendedCarIds();
        getUserFav();
        ConnectDatabase dp=new ConnectDatabase();
        Iterator<String> iterator = MatchUser.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            int c = 0;
            Connection conn = dp.connectDB();
            try {
                Statement stmt = conn.createStatement();
                ResultSet R = stmt.executeQuery("select * from Favorits where UserID = '" + name + "'");
                R.next();
                do {
                    boolean is = true;
                    setGetRecomendedCarIds();
                    for (int f = 0; f < getRecomendedCarIDS.size(); f++) {
                        if (R.getString(2).equals(getRecomendedCarIDS.get(f))) {
                            is = false;
                        }
                    }
                    if (is && !(R.getString(2).equals(CarID))&&!allfav.contains(R.getString(2))) {
                        RcomendItem.add(R.getString(2));
                    }
                } while (R.next());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void insertintoDataBase(){
        ConnectDatabase db = new ConnectDatabase();
        java.sql.Connection con = db.connectDB();
        for(int i=0;i<RcomendItem.size();i++) {
            try {
                Statement stmt = con.createStatement();
                int x = stmt.executeUpdate("insert into Recomended values('" + UserID + "','" + RcomendItem.get(i) + "')");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
    }

}
