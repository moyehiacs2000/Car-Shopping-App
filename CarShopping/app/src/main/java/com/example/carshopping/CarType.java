package com.example.carshopping;

import java.util.ArrayList;

public class CarType {
    public String TypeNo,BrandNo,BrandName,CarName,Price,Mileage,txtautomatic,txtL,txtsuv,txtPetrol,txtdoor,txtSeats,txtfull,partDesc;
    public ArrayList<String>photos=new  ArrayList<String>();
    public String getTypeNo() {
        return TypeNo;
    }

    public String getBrandNo() {
        return BrandNo;
    }

    public String getBrandName() {
        return BrandName;
    }

    public String getCarName() {
        return CarName;
    }

    public String getPrice() {
        return Price;
    }

    public String getMileage() {
        return Mileage;
    }

    public String getTxtautomatic() {
        return txtautomatic;
    }

    public String getTxtL() {
        return txtL;
    }

    public String getTxtsuv() {
        return txtsuv;
    }

    public String getTxtPetrol() {
        return txtPetrol;
    }

    public String getTxtdoor() {
        return txtdoor;
    }

    public String getTxtSeats() {
        return txtSeats;
    }

    public String getTxtfull() {
        return txtfull;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setTypeNo(String typeNo) {
        TypeNo = typeNo;
    }

    public void setBrandNo(String brandNo) {
        BrandNo = brandNo;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setMileage(String mileage) {
        Mileage = mileage;
    }

    public void setTxtautomatic(String txtautomatic) {
        this.txtautomatic = txtautomatic;
    }

    public void setTxtL(String txtL) {
        this.txtL = txtL;
    }

    public void setTxtsuv(String txtsuv) {
        this.txtsuv = txtsuv;
    }

    public void setTxtPetrol(String txtPetrol) {
        this.txtPetrol = txtPetrol;
    }

    public void setTxtdoor(String txtdoor) {
        this.txtdoor = txtdoor;
    }

    public void setTxtSeats(String txtSeats) {
        this.txtSeats = txtSeats;
    }

    public void setTxtfull(String txtfull) {
        this.txtfull = txtfull;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
}
