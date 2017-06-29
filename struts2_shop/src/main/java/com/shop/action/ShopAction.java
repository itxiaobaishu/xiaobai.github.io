package com.shop.action;

import java.util.List;

import com.shop.bean.Shop;
import com.shop.service.ShopService;

public class ShopAction {
    
    private ShopService shopService = new ShopService();
    
    private Shop shop;
    
    private List<Shop> list;
    
    private String ids;
    
    private int count;
    
    private int i;
    
    private int page;
    
    //更新
    public String shopUpdate() {
        shop = this.shopService.shopUpdate(ids);
        return "shopUpdate";
    }
    public String shopUpdateSuccess() {
        this.shopService.shopUpdateSuccess(shop);
        return "shopUpdateSuccess";
    }
    
    //删除
    public String shopDelete() {
        this.shopService.shopDelete(ids);
        return "shopDelete";
    }
    
    //添加
    public String shopAdd() {
        return "shopAdd";
    }
    public String shopAddSuccess() {
        
        this.shopService.shopAddSuccess(shop);
        return "shopAddSuccess";
    }
    //查看
    public String shopList() {
        page = i;
        list = this.shopService.shopList(shop,i);
        count = this.shopService.shopCount(shop);
        return "shopList";
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getList() {
        return list;
    }

    public void setList(List<Shop> list) {
        this.list = list;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    
    

}
