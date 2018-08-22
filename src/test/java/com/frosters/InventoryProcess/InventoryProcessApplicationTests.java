package com.frosters.InventoryProcess;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
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
	public void testRetrieveAllCustomer() throws Exception {
		mockMVC.perform(get("/customerAPI/customer")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$customerId").value(0));
	
	}
	
	@Test
	public void testGetCustomer() throws Exception{
		
		mockMVC.perform(get("/customerAPI/customer/0")).andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$customerName").value("Anu"));
		
	}
	
	@Test
	public void testaddCustomer() throws Exception{
		Customer customer = new Customer(Long.valueOf(2), "Akhil",Long.valueOf(1234567890), "Madurai", "Male");
		byte[] requestJSON = toJson(customer);
		mockMVC.perform(post("/customerAPI").content(requestJSON)).andExpect(status().isOk());
		mockMVC.perform(get("/customerAPI/customer/2")).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$customerId").value(2));
	
	}
	
	@Test
	public void testUpdateCustomer() throws Exception{
		Customer customer = new Customer(Long.valueOf(0), "Anu",Long.valueOf(1234567891), "Chennai", "Female");
		byte[] requestJSON = toJson(customer);
		System.out.println(requestJSON);
		mockMVC.perform(put("/customerAPI/customer/1").content(requestJSON)).andExpect(status().isOk());
		mockMVC.perform(get("/customerAPI/customer/1")).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$contactNumber").value(1234567891));
	
	}
	
	@Test
	public void testDeleteCustomer() throws Exception{
		mockMVC.perform(delete("/customerAPI/customer/2")).andExpect(status().isAccepted());
		mockMVC.perform(delete("/customerAPI/customer")).andExpect(status().isAccepted());
	}

	 private byte[] toJson(Object r) throws Exception {
	        ObjectMapper map = new ObjectMapper();
	        return map.writeValueAsString(r).getBytes();
	    }

}


