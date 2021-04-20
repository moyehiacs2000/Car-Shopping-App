package com.example.carshopping;

import android.app.Activity;
import android.graphics.Bitmap;
import android.telecom.Connection;
import android.util.AndroidException;
import android.util.AndroidRuntimeException;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tayba on 7/4/2020.
 */

public class imageDataBase extends Activity {
     byte []byteArray;
     String encodedImage=null;
    String msg="unknown";
    ConnectDatabase condp;
    public String convertImageIntoString( Bitmap originBitmap){
        if(originBitmap!=null){
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            originBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            byteArray=stream.toByteArray();
            encodedImage= Base64.encodeToString(byteArray,Base64.DEFAULT);
        }
        return encodedImage;
    }
    public void saveImageInDP(Activity activity,String UserID){
        condp=new ConnectDatabase();
        java.sql.Connection con=  condp.connectDB();
        if (con == null) {
            Toast.makeText(activity, "Please Check Internet Access", Toast.LENGTH_LONG).show();
        }
        else {
            try {

                Statement stmt = con.createStatement();
                if(encodedImage.equals(null)){
                    Toast.makeText(activity, "NullPointerException", Toast.LENGTH_LONG).show();

                }
                int x = stmt.executeUpdate("UPDATE Usertable SET photo ='" + encodedImage + "' WHERE ID = '"+UserID+"'");
                if (x == 0) {
                    Toast.makeText(activity, "Sorry Try again", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity, "Image Saved", Toast.LENGTH_LONG).show();
                }
            } catch (SQLException e) {
                Toast.makeText(activity, "SQLException", Toast.LENGTH_LONG).show();
            } catch (IOError e) {
                Toast.makeText(activity, "IOError", Toast.LENGTH_LONG).show();
            } catch (AndroidRuntimeException e) {
                Toast.makeText(activity,"AndroidRuntimeException", Toast.LENGTH_LONG).show();
            } catch (NullPointerException e) {
                Toast.makeText(activity, "NullPointerException", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(activity, "Exception", Toast.LENGTH_LONG).show();
            }
        }

    }
}
