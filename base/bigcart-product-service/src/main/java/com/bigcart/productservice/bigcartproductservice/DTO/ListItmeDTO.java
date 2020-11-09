package com.bigcart.productservice.bigcartproductservice.DTO;



import java.util.ArrayList;

public class ListItmeDTO  {
    ArrayList<ItemDTO> list = new ArrayList<>();
    public ListItmeDTO(){}
    public ListItmeDTO(ArrayList<ItemDTO> list) {
        this.list = list;
    }

    public ArrayList<ItemDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ItemDTO> list) {
        this.list = list;
    }

}