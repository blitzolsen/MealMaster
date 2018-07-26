package com.blogspot.stettsen.mealmaster;


public class Food {

    private String name;
    private Integer quantity;
    private Fraction quantityFraction;
    private String amountType;
    private String barcode;
    private Integer calories;
    private String foodGroup;
    private boolean inCart;

    public Food() {
        inCart = false;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }
    public String getAmountType() {
        return amountType;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
    public Integer getCalories() {
        return calories;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }
    public String getFoodGroup() {
        return foodGroup;
    }

    public void setQuantityFraction(Fraction quantityFraction) {
        this.quantityFraction = quantityFraction;
    }

    public Fraction getQuantityFraction() {
        return quantityFraction;
    }

    public void toggleInCart() {
        if(inCart == false) {
            inCart = true;
        } else {
            inCart = false;
        }
    }

    public boolean isInCart() {
        return inCart;
    }

    public void setInCart(boolean inCart) {
        this.inCart = inCart;
    }
}
