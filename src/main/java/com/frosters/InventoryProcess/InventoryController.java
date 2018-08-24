package com.frosters.InventoryProcess;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/inventoryAPI")
public class InventoryController {

	@Autowired
	InventoryRepository inventoryRepository;
	
	@GetMapping("/inventory")
	List<Inventory> getAllInventorys(){
	 return inventoryRepository.findAll();	
	}
	
	@GetMapping("/inventory/{id}")
	Inventory getInventory(@PathVariable("id") Long id) {
		return inventoryRepository.getOne(id);
	}
	 
	@PostMapping("/inventory")
	@ResponseBody Inventory addNewInventory(@RequestBody Inventory inventory) {
		return inventoryRepository.save(inventory);
	}
	
	@PutMapping("/inventory/{id}")
	@ResponseBody Inventory updateInventory(@PathVariable("id")  Long id, @RequestBody  Inventory updatedInventory) throws Exception {
		Inventory inventory = inventoryRepository.findById(id).get();
		  if(inventory== null) {
			throw new Exception();  
		  }else {
			  System.out.println(updatedInventory);
	            return inventoryRepository.save(updatedInventory);
		  }
		
	}
	
	@DeleteMapping("/inventory/{id}")
   public void deleteInventory(@PathVariable("id")  Long id) throws Exception {
		  Inventory inventory = inventoryRepository.findById(id).get();
		  if(inventory== null) {
			throw new Exception();  
		  }else {
			  inventoryRepository.deleteById(id);
		  }
	}
	
	@DeleteMapping("/inventory")
	   public void deleteInventory() {
			 inventoryRepository.deleteAll();
		}
}
