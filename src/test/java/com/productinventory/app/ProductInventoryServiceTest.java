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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.productinventory.bean.InventoryItem;
import com.productinventory.persistence.ProductInventoryDao;
import com.productinventory.service.ProductInventoryServiceImpl;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductInventoryServiceTest {
	@Autowired
	@InjectMocks
	private ProductInventoryServiceImpl productInventoryService;

	@Mock
	private ProductInventoryDao productInventoryDao;

	private AutoCloseable autoCloseable;

	private List<InventoryItem> items;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable = MockitoAnnotations.openMocks(this);

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
		autoCloseable.close();
	}

	@Test
	void getInventoryItemByProductCodeOne() {
		String productCode = "A001";
		InventoryItem item = items.stream().filter(i -> i.getProductCode().equals(productCode)).collect(Collectors.toList()).get(0);
		Mockito.when(productInventoryDao.findByProductCode(productCode)).thenReturn(item);
		
		assertEquals(item, productInventoryService.getInventoryItemByProductCode(productCode));
	}
	
	@Test
	void getInventoryItemByProductCodeTwo() {
		String productCode = "A006";
		InventoryItem item = null;
		Mockito.when(productInventoryDao.findByProductCode(productCode)).thenReturn(item);
		
		assertEquals(item, productInventoryService.getInventoryItemByProductCode(productCode));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeOne() {
		String productCode = "A001";
		int availableQuantity = 105;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(1);
	
		assertTrue(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeTwo() {
		String productCode = "A006";
		int availableQuantity = 106;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(0);
	
		assertFalse(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeThree() {
		String productCode = "A005";
		int availableQuantity = -10;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(0);
	
		assertFalse(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
	
	@Test
	void updateInventoryItemQuantityByProductCodeFour() {
		String productCode = "A006";
		int availableQuantity = -10;
		Mockito.when(productInventoryDao.updateInventoryItemQuantityByProductCode(productCode, availableQuantity)).thenReturn(0);
	
		assertFalse(productInventoryService.updateInventoryItemQuantityByProductCode(productCode, availableQuantity));
	}
}