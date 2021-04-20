package com.example.carshopping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class getRecomended {
    private String UserID;
    private ArrayList<String> getRecomendedCarIDS;
    ConnectDatabase dp = new ConnectDatabase();

    public void setGetRecomendedCarIds(String UserID) {
        this.UserID=UserID;
        getRecomendedCarIDS=new ArrayList<String>();
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

    public ArrayList<CarType> getRecomendedCar() {
        Connection conn = dp.connectDB();
        ArrayList<CarType>Cars=new  ArrayList<CarType>();
        for (int i = 0; i < getRecomendedCarIDS.size(); i++) {
            CarType car = new CarType();
            String selectQuery = "select * from CarType where TypeNo='" + getRecomendedCarIDS.get(i) + "'";
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet t = stmt.executeQuery(selectQuery);
                t.next();
                car.setTypeNo(t.getString(1));
                car.setBrandNo(t.getString(2));
                car.setBrandName(t.getString(3));
                car.setCarName(t.getString(4));
                car.setPrice(t.getString(5));
                car.setMileage(t.getString(6));
                String selectQuery2 = "select * from TypePhotos where TypeNo='" + getRecomendedCarIDS.get(i) + "'";
                try {
                    ResultSet p = stmt.executeQuery(selectQuery2);
                    p.next();
                    do {
                        car.photos.add(p.getString(3));
                    } while (p.next());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Cars.add(car);
        }
        return Cars;
    }
}

