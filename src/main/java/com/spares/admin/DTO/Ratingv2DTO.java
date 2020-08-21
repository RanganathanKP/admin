package com.spares.admin.DTO;

public class Ratingv2DTO {

    private Integer productID;
    private Float rating=0.0f;
    private Boolean customerPrefered=false;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getCustomerPrefered() {
        return customerPrefered;
    }

    public void setCustomerPrefered(Boolean customerPrefered) {
        this.customerPrefered = customerPrefered;
    }

}
