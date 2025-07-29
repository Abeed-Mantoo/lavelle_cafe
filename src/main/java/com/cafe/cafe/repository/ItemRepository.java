package com.cafe.cafe.repository;

import com.cafe.cafe.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // You get findAll(), save(), etc. automatically
}
