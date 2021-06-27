package com.example.restaurantlistjpa.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchLocalRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<searchLocalItems> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class searchLocalItems{
        //inner class

        private String category;
        private String title;
        private String link;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private int mapx;
        private int mapy;


    }
}
