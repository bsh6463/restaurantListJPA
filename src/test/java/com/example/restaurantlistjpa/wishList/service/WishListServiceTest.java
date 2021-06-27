package com.example.restaurantlistjpa.wishList.service;

import com.example.restaurantlistjpa.wishList.repository.WishListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListServiceTest {

    @Autowired
    WishListService wishListService;
    @Autowired
    WishListRepository wishListRepository;

    @Test
    public void searchTest(){

        var result = wishListService.search("돼지");


        //wishListService.add(result);
        System.out.println(result);
       // var title = result.getTitle();
       // var savedEntity = wishListRepository.findByTitle(title);
       // System.out.println("----------");
       // System.out.println(savedEntity);
       // System.out.println("----------");
        Assertions.assertNotNull(result);

    }

    @Test
    public void searchAddReadTest(){

        var result1 = wishListService.search("돼지");
        var result2 = wishListService.search("소");
        var result3 = wishListService.search("회");

        wishListService.add(result1);
        wishListService.add(result2);
        wishListService.add(result3);





        wishListService.addVisit(2);
        wishListService.addVisit(2);
        wishListService.addVisit(2);

        var result = wishListService.findAll();
        result.forEach(System.out::println);
        Assertions.assertEquals(3, wishListRepository.findById(2).get().getVisitCount());

    }






}