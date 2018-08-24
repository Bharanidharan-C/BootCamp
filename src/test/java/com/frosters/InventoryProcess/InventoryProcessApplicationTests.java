package com.frosters.InventoryProcess;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test; 
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryProcessApplicationTests {

	private MockMvc mockMVC;
	
	@Autowired
	WebApplicationContext context;
	 
	@Test
	public void contextLoads() {
	}
	
	@Before
	public void setUp() {
		mockMVC = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test_2_RetrieveAllCustomer() throws Exception {
		mockMVC.perform(get("/customerAPI/customer")).andDo(print())
		.andExpect(status().isOk());
	
	
	}
	
	@Test
	public void test_1_GetCustomer() throws Exception{
		
		mockMVC.perform(get("/customerAPI/customer/0")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(0));
		
	}
	
	@Test
	public void test_3_addCustomer() throws Exception{
		Customer customer = new Customer();
		customer.setCustomerId(Long.valueOf(2));
		customer.setCustomerName("Akash");
		customer.setAddress("Chennai");
		customer.setContactNumber(Long.valueOf(1234567890));
		customer.setGender("male");
		String requestJSON = toJson(customer);
		mockMVC.perform(post("/customerAPI/customer").content(requestJSON).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		mockMVC.perform(get("/customerAPI/customer/2")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(2));
	
	}
	
	@Test
	public void test_4_UpdateCustomer() throws Exception{
		Customer customer = new Customer(Long.valueOf(0), "Anu",Long.valueOf(1234567891), "Chennai", "Female");
	String requestJSON = toJson(customer);
		System.out.println("requestJSON"+requestJSON);
		mockMVC.perform(put("/customerAPI/customer/1").content(requestJSON).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		mockMVC.perform(get("/customerAPI/customer/1")).andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.contactNumber").value(1234567891));
	
	}
	
	@Test
	public void test_5_DeleteCustomer() throws Exception{
		mockMVC.perform(delete("/customerAPI/customer/1")).andExpect(status().isOk());
		mockMVC.perform(delete("/customerAPI/customer")).andExpect(status().isOk());
	}
	
	@Test
	public void test_6_RetrieveAllVendor() throws Exception {
		mockMVC.perform(get("/vendorAPI/vendor")).andDo(print())
		.andExpect(status().isOk());
	
	
	}
	
	@Test
	public void test_7_GetVendor() throws Exception{
		
		mockMVC.perform(get("/vendorAPI/vendor/0")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.vendorId").value(0));
		
	}
	
	@Test
	public void test_8_addVendor() throws Exception{
		Vendor vendor = new Vendor(Long.valueOf(2), "vendor2", Long.valueOf(1234567890), "123@xx.com", "vendor2", "chennai");
		
		String requestJSON = toJson(vendor);
		mockMVC.perform(post("/vendorAPI/vendor").content(requestJSON).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		mockMVC.perform(get("/vendorAPI/vendor/2")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.vendorId").value(2));
	
	}
	
	@Test
	public void test_9_UpdateVendor() throws Exception{
		Vendor vendor = new Vendor(Long.valueOf(1), "vendor2", Long.valueOf(1234567890), "123@xx.com", "vendor2", "chennai");
	String requestJSON = toJson(vendor);
		System.out.println("requestJSON"+requestJSON);
		mockMVC.perform(put("/vendorAPI/vendor/1").content(requestJSON).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		mockMVC.perform(get("/vendorAPI/vendor/1")).andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.vendorContactNo").value(1234567890));
	
	}
	
	@Test
	public void test_10_DeleteVendor() throws Exception{
		mockMVC.perform(delete("/vendorAPI/vendor/1")).andExpect(status().isOk());
		mockMVC.perform(delete("/vendorAPI/vendor")).andExpect(status().isOk());
	}
	@Test
	public void test_11_RetrieveAllInventory() throws Exception {
		mockMVC.perform(get("/inventoryAPI/inventory")).andDo(print())
		.andExpect(status().isOk());
	
	
	}
	
	@Test
	public void test_12_GetInventory() throws Exception{
		
		mockMVC.perform(get("/inventoryAPI/inventory/0")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.skuId").value(0));
		
	}
	
	@Test
	public void test_13_addInventory() throws Exception{
		Inventory inventory = new Inventory(Long.valueOf(2),"MI A2","MI ANDROID ONE VERSION 2.0", 10, 3, 16000.00);
		String requestJSON = toJson(inventory);
		mockMVC.perform(post("/inventoryAPI/inventory").content(requestJSON).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		mockMVC.perform(get("/inventoryAPI/inventory/2")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.skuId").value(2));
	
	}
	
	@Test
	public void test_14_UpdateInventory() throws Exception{
		Inventory inventory = new Inventory(Long.valueOf(1),"MI A1","MI ANDROID ONE VERSION", 10, 3, 15000.00);
		String requestJSON = toJson(inventory);
		System.out.println("requestJSON"+requestJSON);
		mockMVC.perform(put("/inventoryAPI/inventory/1").content(requestJSON).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		mockMVC.perform(get("/inventoryAPI/inventory/1")).andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(15000.00));
	
	}
	
	@Test
	public void test_15_DeleteInventory() throws Exception{
		mockMVC.perform(delete("/inventoryAPI/inventory/1")).andExpect(status().isOk());
		mockMVC.perform(delete("/inventoryAPI/inventory")).andExpect(status().isOk());
	}


	 private String toJson(Object r) throws Exception {
	        ObjectMapper map = new ObjectMapper();
	        return map.writeValueAsString(r);
	    }

}


