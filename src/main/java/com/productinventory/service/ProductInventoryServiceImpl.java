package com.productinventory.service;

import com.productinventory.bean.InventoryItem;

public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Override
	public InventoryItem getInventoryItemByProductCode(String productCode) {
		return null;
	}

	@Override
	public boolean updateInventoryItemQuantityByProductCode(String productCode, int availableQuantity) {
		return false;
	}
}