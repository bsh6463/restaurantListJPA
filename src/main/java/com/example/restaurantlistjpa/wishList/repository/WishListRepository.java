package com.example.restaurantlistjpa.wishList.repository;

import com.example.restaurantlistjpa.wishList.entirty.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishListEntity, Integer> {


    WishListEntity findByTitle(String title);
}
