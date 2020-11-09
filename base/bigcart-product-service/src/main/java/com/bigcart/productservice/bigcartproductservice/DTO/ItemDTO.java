package com.bigcart.productservice.bigcartproductservice.DTO;

import java.io.Serializable;

public class ItemDTO {
    private long itemtId;
    private long vendorId;
    private int quantity;

    public ItemDTO() {}
    public ItemDTO(long itemtId, long vendorId, int quantity) {
        this.itemtId = itemtId;
        this.vendorId = vendorId;
        this.quantity = quantity;
    }

    public long getItemtId() {
        return itemtId;
    }

    public void setItemtId(long itemtId) {
        this.itemtId = itemtId;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
