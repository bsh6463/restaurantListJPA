package com.example.restaurantlistjpa.wishList.entirty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WishListEntity {

    @Id
    @GeneratedValue
    private int id;

    //wish list에 저장하고자 하는 내용
    private String category;
    private String title;
    private String link;
    private String address;
    private String roadAddress;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate;



}
