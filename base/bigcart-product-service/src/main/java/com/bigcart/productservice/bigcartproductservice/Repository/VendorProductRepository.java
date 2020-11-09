package com.bigcart.productservice.bigcartproductservice.Repository;

import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProductCKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorProductRepository extends JpaRepository<VendorProduct, VendorProductCKey> {
    VendorProduct findByVendorId(Long id);
    List<VendorProduct> findAllByVendorId(Long id);

}
