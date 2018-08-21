package com.frosters.InventoryProcess;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerAPI/")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/customer")
	List<Customer> getAllCustomers(){
	 return customerRepository.findAll();	
	}
	
	@GetMapping("/customer/{id}")
	Customer getCustomer(@PathVariable Long id) {
		return customerRepository.getOne(id);
	}
	
	@PostMapping("/customer")
	Customer addNewCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	@PutMapping("/customer")
	Customer updateCustomer(Long id, Customer updatedCustomer) throws Exception {
		 return customerRepository.findById(id).map(customer -> {
			 customer.setContactNumber(updatedCustomer.getContactNumber());
	            return customerRepository.save(updatedCustomer);
	        }).orElseThrow(() -> new Exception());
	}
	
	@DeleteMapping("/customer/{id}")
   public void deleteCustomer( Long id) throws Exception {
		  Customer customer = customerRepository.findById(id).get();
		  if(customer== null) {
			throw new Exception();  
		  }else {
			  customerRepository.deleteById(id);
		  }
	}
	
	@DeleteMapping("/customer")
	   public void deleteCustomer() {
			 customerRepository.deleteAll();
		}
}
