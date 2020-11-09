package com.bigcart.productservice.bigcartproductservice.DTO;

import com.bigcart.productservice.bigcartproductservice.Model.Category;
import com.bigcart.productservice.bigcartproductservice.Model.Product;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Id
//@JsonIgnore
//@Column(name = "product_id")
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//private Long productId;
//@Column(insertable = false,updatable = false)
//private Long categoryId;
//private String name;
//private String description;
//private String specifications;

//Relations
//@ManyToOne
//@JsonIgnore
//@JoinColumn(name = "categoryId")
//private Category category;
//
//@OneToMany()
//@JsonIgnore
//@JoinColumn(name = "product_id",referencedColumnName = "product_id")
//private List<VendorProduct> VendorProduct = new ArrayList<>();
//@Id
//private Long vendorId;
//@Id
//@Column(name = "product_id")
//private Long productId;
//
//private LocalDateTime requestDate;
//private LocalDateTime approvalDate;
//private LocalDateTime modificationDate;
//private Integer quantity;
//private Float price;
//private String status;
//private String imageUrl;

public class FullProductDTO {

    private Category category;
    private Product product;
    private VendorProduct vendorProduct;

    public FullProductDTO() {
    }

    public FullProductDTO(Category category, Product product, VendorProduct vendorProduct) {
        this.category = category;
        this.product = product;
        this.vendorProduct = vendorProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public VendorProduct getVendorProduct() {
        return vendorProduct;
    }

    public void setVendorProduct(VendorProduct vendorProduct) {
        this.vendorProduct = vendorProduct;
    }
}
