package com.productinventory.service;

import com.productinventory.bean.InventoryItem;

public interface ProductInventoryService {
	InventoryItem getInventoryItemByProductCode(String productCode);
	
	boolean updateInventoryItemQuantityByProductCode(String productCode, int availableQuantity);
}
