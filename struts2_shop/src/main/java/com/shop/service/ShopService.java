package com.shop.service;

import java.util.List;

import com.shop.bean.Shop;
import com.shop.dao.ShopDao;

public class ShopService {

    private ShopDao shopDao = new ShopDao();

   
    public List<Shop> shopList(Shop shop, int i) {
        return this.shopDao.shopList(shop,i);
    }


    public void shopAddSuccess(Shop shop) {
        this.shopDao.shopAddSuccess(shop);
    }


    public void shopDelete(String ids) {
        this.shopDao.shopDelete(ids);
    }


    public Shop shopUpdate(String ids) {
        return this.shopDao.shopUpdate(ids);
    }


    public void shopUpdateSuccess(Shop shop) {
        this.shopDao.shopUpdateSuccess(shop);
    }


    public int shopCount(Shop shop) {
        
        return this.shopDao.shopCount(shop);
    }

}
