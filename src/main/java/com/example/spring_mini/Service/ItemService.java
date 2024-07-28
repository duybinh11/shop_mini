package com.example.spring_mini.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.spring_mini.DTO.Request.RequestAddItem;
import com.example.spring_mini.DTO.Response.ItemResponse;
import com.example.spring_mini.DTO.Response.ResponseData;
import com.example.spring_mini.Entity.ItemEntity;
import com.example.spring_mini.Repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ResponseData getAllItem(int page, int size) {
        page--;
        Pageable pageable = PageRequest.of(page, size);
        Page<ItemEntity> pageEntity = itemRepository.findAll(pageable);
        List<ItemEntity> listItemEnity = pageEntity.getContent();
        int pageTotal = pageEntity.getTotalPages();
        return new ResponseData(
                HttpStatus.OK,
                "lay du lieu thanh cong",
                convertToListItemRessponse(listItemEnity),
                pageTotal);
    }

    public ResponseData addItem(RequestAddItem requestAddItem) {
        ItemEntity itemEntity = ItemEntity.builder().name(requestAddItem.getName()).price(requestAddItem.getPrice())
                .build();
        ItemEntity entity = itemRepository.save(itemEntity);
        ItemResponse itemResponse = convertItemResponse(entity);
        return new ResponseData(HttpStatus.OK, "Them du lieu  thanh cong", itemResponse);
    }

    public ResponseData removeItem(int id) {
        itemRepository.deleteById(id);
        return new ResponseData(HttpStatus.OK, "Xoa thanh cong");
    }

    public List<ItemResponse> convertToListItemRessponse(List<ItemEntity> listItemEnity) {
        return listItemEnity.stream().map((item) -> {
            return convertItemResponse(item);
        }).toList();
    }

    public ItemResponse convertItemResponse(ItemEntity item) {
        return ItemResponse.builder().id(item.getId()).name(item.getName()).price(item.getPrice()).build();
    }
}
