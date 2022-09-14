package com.productinventory.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productinventory.bean.InventoryItem;

public interface ProductInventoryDao extends JpaRepository<InventoryItem, Long> {

}
