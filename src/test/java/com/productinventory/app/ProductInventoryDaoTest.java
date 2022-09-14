package com.productinventory.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.productinventory.bean.InventoryItem;
import com.productinventory.persistence.ProductInventoryDao;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductInventoryDaoTest {
	@Autowired
	private ProductInventoryDao productInventoryDao;
	
	private List<InventoryItem> items;
	
	@BeforeEach
	void setUp() throws Exception {
		InventoryItem item1 = new InventoryItem(1, "A001", 100);
		InventoryItem item2 = new InventoryItem(2, "A002", 200);
		InventoryItem item3 = new InventoryItem(3, "A003", 300);
		InventoryItem item4 = new InventoryItem(4, "A004", 400);
		InventoryItem item5 = new InventoryItem(5, "A005", 500);
		
		items = new ArrayList<>();
		items.addAll(Arrays.asList(item1, item2, item3, item4, item5));
	}

	@AfterEach
	void tearDown() throws Exception {
		items.clear();
	}

	@Test
	void findByProductCodeOne() {
		String productCode = "A003";
		InventoryItem item = items.stream().filter(i -> i.getProductCode().equals(productCode)).collect(Collectors.toList()).get(0);
		
		assertEquals(item, productInventoryDao.findByProductCode(productCode));
	}
	
	@Test
	void findByProductCodeTwo() {
		String productCode = "A006";
		
		assertEquals(null, productInventoryDao.findByProductCode(productCode));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeOne() {
		String productCode = "A005";
		InventoryItem item = productInventoryDao.findByProductCode(productCode);
		int availableQuantity = item.getAvailableQuantity() + 5;
		
		assertTrue(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeTwo() {
		String productCode = "A006";
		int availableQuantity = 605;
		
		assertFalse(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeThree() {
		String productCode = "A005";
		int availableQuantity = -10;
		
		assertFalse(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeFour() {
		String productCode = "A006";
		int availableQuantity = -10;
		
		assertFalse(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity) == 1);
	}
}