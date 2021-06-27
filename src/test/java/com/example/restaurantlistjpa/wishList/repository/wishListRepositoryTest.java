package com.example.restaurantlistjpa.wishList.repository;

import com.example.restaurantlistjpa.wishList.entirty.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class wishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity createWishListEntity(){

        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setAddress("Address");
        wishList.setCategory("category");
        wishList.setLink("link");
        wishList.setImageLink("img link");
        wishList.setVisit(false);
        wishList.setLastVisitDate(null);
        wishList.setRoadAddress("road address");
        wishList.setVisitCount(0);

        return wishList;

    }

    @Test
    public void crudTest(){

        //create
        WishListEntity wishListEntity1 = createWishListEntity();
        WishListEntity wishListEntity2 = createWishListEntity();

        var expected1 = wishListRepository.save(wishListEntity1);
        var expected2 = wishListRepository.save(wishListEntity2);

        //read
        Assertions.assertEquals(1, expected1.getId());
        Assertions.assertEquals(2, expected2.getId());

        //delete
        wishListRepository.deleteById(2);

        wishListRepository.findAll();

        //update
        expected1.setCategory("고기");

        Assertions.assertEquals("고기", expected1.getCategory());
    }


}