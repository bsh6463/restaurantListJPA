package com.example.restaurantlistjpa.wishList.service;

import com.example.restaurantlistjpa.naver.NaverClient;
import com.example.restaurantlistjpa.naver.dto.SearchImageReq;
import com.example.restaurantlistjpa.naver.dto.SearchLocalReq;
import com.example.restaurantlistjpa.wishList.dto.WishListDto;
import com.example.restaurantlistjpa.wishList.entirty.WishListEntity;
import com.example.restaurantlistjpa.wishList.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {
    //Naver로부터 받은 정보 저장이 필요
    private final NaverClient naverClient; //final!!!

    //WishListRepository에 저장
    private final WishListRepository wishListRepository; //final!!


   public WishListDto search(String query){

       //local 정보
       var searchLocalReq = new SearchLocalReq();
       searchLocalReq.setQuery(query);

       var searchLocalRes = naverClient.localSearch(searchLocalReq);

       //local 정보가 있는 경우에 image 정보 찾아옴
       if(searchLocalRes.getTotal() > 0){
           //첫 번째 검색 결과에 대해 제목으로 이미지 검색함.
           var localItem = searchLocalRes.getItems().stream().findFirst().get();

           var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");//tag제거 정규식

           var searchImageReq = new SearchImageReq();
           searchImageReq.setQuery(imageQuery);

           var searchImageRes = naverClient.imageSearch(searchImageReq);

           //이미지에 대한 검색 결과가 존재하면 result 반환
           if(searchImageRes.getTotal() > 0){

               var imageItem = searchImageRes.getItems().stream().findFirst().get();

               var result = new WishListDto();
               result.setTitle(localItem.getTitle());
               result.setLink(localItem.getLink());
               result.setCategory(localItem.getCategory());
               result.setAddress(localItem.getAddress());
               result.setRoadAddress(localItem.getRoadAddress());
               result.setImageLink(imageItem.getLink());

               return result;
           }


       }
       return new WishListDto();
   }


    public WishListDto add(WishListDto wishListDto){

        var entity = DtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        var title = saveEntity.getTitle();
        return EntityToDto(wishListRepository.findByTitle(title));

    }

    public void delete(int id){

        wishListRepository.deleteById(id);

    }

    public void addVisit(int id){

        var wishItem = wishListRepository.findById(id);

        if(wishItem.isPresent()){
           var item =  wishItem.get();

           item.setVisit(true);
           int addCount = item.getVisitCount() + 1;
           item.setVisitCount(addCount);
        }
    }

    public List<WishListDto> findAll(){
        return wishListRepository.findAll().stream().map(it -> EntityToDto(it)).collect(Collectors.toList());
    }

    public WishListDto findById(int id){

       var entity = wishListRepository.findById(id).get();
       return EntityToDto(entity);

    }
    private WishListDto EntityToDto(WishListEntity wishListEntity){

        var dto = new WishListDto();
        dto.setAddress(wishListEntity.getAddress());
        dto.setCategory(wishListEntity.getCategory());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setLink(wishListEntity.getLink());
        dto.setTitle(wishListEntity.getTitle());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        dto.setId(wishListEntity.getId());
        dto.setVisit(wishListEntity.isVisit());


        return dto;
    }

    private WishListEntity DtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
        entity.setAddress(wishListDto.getAddress());
        entity.setCategory(wishListDto.getCategory());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setLink(wishListDto.getLink());
        entity.setTitle(wishListDto.getTitle());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        entity.setId(wishListDto.getId());
        entity.setVisit(wishListDto.isVisit());

        return entity;
    }




}
