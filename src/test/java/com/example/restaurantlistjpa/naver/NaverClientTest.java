package com.example.restaurantlistjpa.naver;

import com.example.restaurantlistjpa.naver.dto.SearchImageReq;
import com.example.restaurantlistjpa.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
//Naver dto및 client 잘 작동하는지 test

@SpringBootTest
class NaverClientTest {

    @Autowired
    NaverClient naverClient;

    @Test
    public void searchLocalTest(){

        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery("치킨");

        var result = naverClient.localSearch(searchLocalReq);

        System.out.println(result);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getLink());
    }

    @Test
    public void searchImageTest(){

        SearchImageReq  searchImageReq = new SearchImageReq();
        searchImageReq.setQuery("치킨");

        var result = naverClient.imageSearch(searchImageReq);

        System.out.println(result);
        Assertions.assertNotNull(result);


    }

}