package com.example.dentistmysql.model;

public class StoreForm {

    private String storeType;

    private int Quantity;

    public StoreForm() {super();}

    public StoreForm (String storeType, int Quantity) {
        super();
        this.storeType = storeType;
        this.Quantity = Quantity;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
