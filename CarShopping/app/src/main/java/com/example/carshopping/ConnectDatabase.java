package com.example.carshopping;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Tayba on 6/27/2020.
 */

public class ConnectDatabase {
    private static String Classes="net.sourceforge.jtds.jdbc.Driver";
    Connection connection=null;
    public Connection connectDB(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            Class.forName(Classes);
            connection= DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.3;databaseName=CarShopping;user=moyehiacs;password=01144831131;");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
