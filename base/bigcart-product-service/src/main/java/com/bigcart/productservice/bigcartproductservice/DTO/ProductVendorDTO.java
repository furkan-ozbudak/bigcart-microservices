package com.bigcart.productservice.bigcartproductservice.DTO;

import java.time.LocalDateTime;

// to fetch existing products
public class ProductVendorDTO {
    /* Given Requirements:
    LIST DTO (ID, Name, Picture URL  , PRICE , QTY, Desc, Specifications

            , Vendor name, List of reviews( name , review))

    Note: Specs String “comma separated values”

    Eg: Specs : (Dimentions,”12*13”,Ram ,12GB ,  )
    */

    private Long productId;
    private Long vendorId;
    private String name;
    private String imageUrl;
    private double price;
    private int quantity;
    private String description;
    private String Specifications;
    private String vendorName;
    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;
    private LocalDateTime modificationDate;

    public ProductVendorDTO() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return Specifications;
    }

    public void setSpecifications(String specifications) {
        Specifications = specifications;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
