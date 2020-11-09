package com.bigcart.productservice.bigcartproductservice.DTO;

public class ApproveProductDTO {
    private String vendorProductId;
    private int approvalCode;

    public ApproveProductDTO() {
    }

    public String getVendorProductId() {
        return vendorProductId;
    }

    public void setVendorProductId(String vendorProductId) {
        this.vendorProductId = vendorProductId;
    }


    public int getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(int approvalCode) {
        this.approvalCode = approvalCode;
    }
}
