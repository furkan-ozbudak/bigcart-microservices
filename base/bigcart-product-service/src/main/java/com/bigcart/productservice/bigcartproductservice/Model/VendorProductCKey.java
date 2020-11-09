package com.bigcart.productservice.bigcartproductservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VendorProductCKey implements Serializable{
    private Long vendorId;
    private Long productId;

 public VendorProductCKey(){}
    public VendorProductCKey(Long vendorId, Long productId) {
        this.productId = productId;
        this.vendorId = vendorId;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ProductVendorCKey)) return false;
//        ProductVendorCKey that = (ProductVendorCKey) o;
//        return vendorId.equals(that.vendorId) &&
//                productId.equals(that.productId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(vendorId, productId);
//    }
}
