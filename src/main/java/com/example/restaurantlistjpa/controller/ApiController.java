package com.example.restaurantlistjpa.controller;

import com.example.restaurantlistjpa.wishList.dto.WishListDto;
import com.example.restaurantlistjpa.wishList.repository.WishListRepository;
import com.example.restaurantlistjpa.wishList.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    private final WishListService wishListService;

    private final WishListRepository wishListRepository;


    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){

       return wishListService.search(query);

    }

    @PostMapping
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}", wishListDto);

        return wishListService.add(wishListDto);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){

        wishListService.delete(id);

    }

    @GetMapping
    public List<WishListDto> findAll(){

        var result = wishListService.findAll();
        result.forEach(System.out::println);
        return result;
    }

    @PostMapping("/{id}")
    public WishListDto addVisit(@PathVariable int id){

        wishListService.addVisit(id);

        return wishListService.findById(id);

    }



}
