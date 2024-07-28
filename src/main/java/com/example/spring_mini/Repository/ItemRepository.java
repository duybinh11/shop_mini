package com.example.spring_mini.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_mini.Entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Integer>{
    
}
