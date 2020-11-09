package com.bigcart.productservice.bigcartproductservice.Model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@IdClass(VendorProductCKey.class)
public class VendorProduct {
    @Id
    private Long vendorId;
    @Id
    @Column(name = "product_id")
    private Long productId;

    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;
    private LocalDateTime modificationDate;
    private Integer quantity;
    private Float price;
    private String status;
    private String imageUrl;
    double rating;
    int ratingCount;

    public VendorProduct() {
        rating = 0;
        ratingCount = 0;
    }

//    @OneToMany(mappedBy = "productVendor")
//    private List<Review> reviews = new ArrayList<>();

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        VendorProduct vendorProduct = (VendorProduct) o;
//        return vendorId.equals(vendorProduct.vendorId) &&
//                productId.equals(vendorProduct.productId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productId, vendorId);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorProduct)) return false;
        VendorProduct that = (VendorProduct) o;
        return vendorId.equals(that.vendorId) &&
                productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorId, productId);
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer qty) {
        this.quantity = qty;
    }

    public void decQty(Integer qty) {
        this.quantity = this.quantity - qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime dateRequested) {
        this.requestDate = dateRequested;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime dateApproved) {
        this.approvalDate = dateApproved;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime dateModified) {
        this.modificationDate = dateModified;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}
