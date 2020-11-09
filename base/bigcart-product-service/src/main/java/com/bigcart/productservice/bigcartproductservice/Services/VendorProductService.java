package com.bigcart.productservice.bigcartproductservice.Services;

import com.bigcart.productservice.bigcartproductservice.DTO.ListItmeDTO;
import com.bigcart.productservice.bigcartproductservice.DTO.ProductForAdminDTO;
import com.bigcart.productservice.bigcartproductservice.Model.Email;
import com.bigcart.productservice.bigcartproductservice.Model.VendorProduct;

import java.util.List;

// return product vendor and combine in controller
public interface VendorProductService {
    List<VendorProduct> findAll();
    List<VendorProduct> findAllByVendorId(Long id);
    List<ProductForAdminDTO> productToProductDTOList(List<VendorProduct> vendorProductList, String status);
    List<ProductForAdminDTO> productToProductDTOList(List<VendorProduct> vendorProductList);
    String getVendorNameByVendorId(String vendorId);
    String getVendorNameByVendorId(Long vendorId);


    VendorProduct findById(Long productId, Long vendorId) ;
    VendorProduct findByVendorId(Long VendorId);
    VendorProduct findByCategoryId(Long CategoryId);
    ProductForAdminDTO productToProductDTO(VendorProduct vendorProduct, String status);

    VendorProduct save(VendorProduct vendorProduct);
    VendorProduct update(VendorProduct vendorProduct);
    VendorProduct delete(VendorProduct vendorProduct);

    void synchronizeRatings();
//    Email sendEmail(Email email);




      public boolean removeProductV(ListItmeDTO items);
//
//    public ProductVendor editProductV(long productID, Product edit_product);
//
//    public ProductVendor deleteProductV(long productId);
}
