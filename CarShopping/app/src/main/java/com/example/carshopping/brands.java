package com.example.carshopping;

public class brands {
    private String BrandId;
    private String BrandName;
    private String Brandimg;

    public brands(String brandName, String brandimg,String BrandId) {
        BrandName = brandName;
        Brandimg = brandimg;
        this.BrandId=BrandId;
    }

    public brands() {
    }

    public String getBrandName() {
        return BrandName;
    }

    public String getBrandimg() {
        return Brandimg;
    }

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public void setBrandimg(String brandimg) {
        Brandimg = brandimg;
    }

    public void setBrandId(String brandId) {
        BrandId = brandId;
    }
}
