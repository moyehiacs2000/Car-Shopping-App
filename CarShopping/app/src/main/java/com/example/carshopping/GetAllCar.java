package com.example.carshopping;

import android.content.Context;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetAllCar {
    ConnectDatabase dp=new ConnectDatabase();
    ConnectDatabase dp2=new ConnectDatabase();
    ConnectDatabase dp3=new ConnectDatabase();
    public ArrayList<CarType>GetCars(Context context){
        ArrayList<CarType> Cars=new ArrayList<CarType>();
        String  selectQuery="select * from CarType";
        Connection conn=dp.connectDB();
        Connection conn2=dp2.connectDB();
        Statement stmt=null;
        Statement stmt2=null;
        try{
            stmt=conn.createStatement();
            ResultSet t=stmt.executeQuery(selectQuery);
            t.next();
            do{
                CarType car=new CarType();
                car.setTypeNo(t.getString(1));
                car.setBrandNo(t.getString(2));
                car.setBrandName(t.getString(3));
                car.setCarName(t.getString(4));
                car.setPrice(t.getString(5));
                car.setMileage(t.getString(6));

                String  selectQuery2="select * from TypePhotos where TypeNo='"+t.getString(1)+"'";
                try {
                    stmt2=conn2.createStatement();
                    ResultSet p = stmt2.executeQuery(selectQuery2);
                    p.next();
                    do{
                        car.photos.add(p.getString(3));
                    }while(p.next());
                }
                catch (SQLException e) {

                    e.printStackTrace();
                }
                Cars.add(car);
            }while(t.next());
        } catch (SQLException e) {
            Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return Cars;
    }
    public CarType getoneCar(String TypeNo){
        Connection conn=dp.connectDB();
        CarType car=new CarType();
        String  selectQuery="select * from CarType where TypeNo='"+TypeNo+"'";
        Statement stmt=null;
        try{
            stmt=conn.createStatement();
            ResultSet t=stmt.executeQuery(selectQuery);
            t.next();
            car.setTypeNo(t.getString(1));
            car.setBrandNo(t.getString(2));
            car.setBrandName(t.getString(3));
            car.setCarName(t.getString(4));
            car.setPrice(t.getString(5));
            car.setMileage(t.getString(6));
            car.setTxtautomatic(t.getString(7));
            car.setTxtL(t.getString(8));
            car.setTxtsuv(t.getString(9));
            car.setTxtPetrol(t.getString(10));
            car.setTxtdoor(t.getString(11));
            car.setTxtSeats(t.getString(12));
            car.setTxtfull(t.getString(13));
            car.setPartDesc(t.getString(15));
            String  selectQuery2="select * from TypePhotos where TypeNo='"+TypeNo+"'";
            try {
                ResultSet p = stmt.executeQuery(selectQuery2);
                p.next();
                do{
                    car.photos.add(p.getString(3));
                }while(p.next());
            }
            catch (SQLException e) {
                e.printStackTrace();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }
    public ArrayList<CarType>GetFavCars(Context context,String userID){
        ArrayList<CarType> Cars=new ArrayList<CarType>();
        String  selectQuery="select * from Favorits where UserID ='"+userID+"'";
        Connection conn=dp.connectDB();
        Connection conn2=dp2.connectDB();
        Connection conn3=dp2.connectDB();
        Statement stmt=null;
        Statement stmt2=null;
        Statement stmt3=null;
        try{
            stmt=conn.createStatement();
            ResultSet t=stmt.executeQuery(selectQuery);
            t.next();
            do{

                String  selectQuery2="select * from CarType where TypeNo='"+t.getString(2)+"'";

                try {
                    stmt2=conn2.createStatement();
                    ResultSet p = stmt2.executeQuery(selectQuery2);
                    p.next();
                    do{
                        CarType car=new CarType();
                        car.setTypeNo(p.getString(1));
                        car.setBrandNo(p.getString(2));
                        car.setBrandName(p.getString(3));
                        car.setCarName(p.getString(4));
                        car.setPrice(p.getString(5));
                        car.setMileage(p.getString(6));
                        String  selectQuery3="select * from TypePhotos where TypeNo='"+t.getString(2)+"'";
                        try {
                            stmt3=conn3.createStatement();
                            ResultSet d = stmt3.executeQuery(selectQuery3);
                            d.next();
                            do{
                                car.photos.add(d.getString(3));
                            }while(d.next());
                        }
                        catch (SQLException e) {
                            e.printStackTrace();}
                        Cars.add(car);
                    }while(p.next());
                }
                catch (SQLException e) {

                    e.printStackTrace();
                }

            }while(t.next());
        } catch (SQLException e) {
            Toast.makeText(context,"Empty",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return Cars;
    }
    public ArrayList<String>GetFavCarsIDS(Context context,String userID){
        ArrayList<String> Cars=new ArrayList<String>();
        String  selectQuery="select * from Favorits where UserID ='"+userID+"'";
        Connection conn=dp.connectDB();
        Statement stmt=null;
        try{
            stmt=conn.createStatement();
            ResultSet t=stmt.executeQuery(selectQuery);
            t.next();
            do{
                Cars.add(t.getString(2));
            }while(t.next());
        } catch (SQLException e) {
            //Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return Cars;
    }
    public ArrayList<CarType>GetBrandCars(Context context,String CarBrand){
        ArrayList<CarType> Cars=new ArrayList<CarType>();
        String  selectQuery="select * from CarType where BrandNo='"+CarBrand+"'";
        Connection conn=dp.connectDB();
        Connection conn2=dp2.connectDB();
        Statement stmt=null;
        Statement stmt2=null;
        try{
            stmt=conn.createStatement();
            ResultSet t=stmt.executeQuery(selectQuery);
            t.next();
            do{
                CarType car=new CarType();
                car.setTypeNo(t.getString(1));
                car.setBrandNo(t.getString(2));
                car.setBrandName(t.getString(3));
                car.setCarName(t.getString(4));
                car.setPrice(t.getString(5));
                car.setMileage(t.getString(6));

                String  selectQuery2="select * from TypePhotos where TypeNo='"+t.getString(1)+"'";
                try {
                    stmt2=conn2.createStatement();
                    ResultSet p = stmt2.executeQuery(selectQuery2);
                    p.next();
                    do{
                        car.photos.add(p.getString(3));
                    }while(p.next());
                }
                catch (SQLException e) {

                    e.printStackTrace();
                }
                Cars.add(car);
            }while(t.next());
        } catch (SQLException e) {
            Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return Cars;
    }
    public ArrayList<CarType>GetSimilarCars(String CarID,String BrandNo){
        ArrayList<String>SimilarCarIDS=new ArrayList<String>();
        ArrayList<CarType> Cars=new ArrayList<CarType>();
        String  selectQuery="select * from CarType where BrandNo='"+BrandNo+"'";
        Connection conn=dp2.connectDB();
        Statement stmt=null;
        try {
            stmt = conn.createStatement();
            ResultSet t = stmt.executeQuery(selectQuery);
            t.next();
            do {
                    SimilarCarIDS.add(t.getString(1));

            } while (t.next());
        }catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<SimilarCarIDS.size();i++){
            if(!(SimilarCarIDS.get(i).equals(CarID))) {

                CarType car=getoneCar(SimilarCarIDS.get(i));
                Cars.add(car);
            }

        }
        return Cars;
    }

}
