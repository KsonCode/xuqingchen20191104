package com.bwie.xuqingchen20191104.bean;

import java.util.List;

public class HomeBean {

    public String message;
    public String status;
    public ResultBean result;

    public static class ResultBean{

        public RxxpBean rxxp;
        public static class RxxpBean{

            public List<ListBean>  commodityList;
           public static class ListBean{
               public String commodityId;
               public String commodityName;
               public String masterPic;
               public String price;
               public String saleNum;
           }
        }
    }
}
