package com.javafun.inventory;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @PostMapping("/check")
    public Boolean checkInventory(@RequestBody Inventory inventory){

        if(inventory.getProductName().equalsIgnoreCase("Soap")){
            return  true;
        }else if(inventory.getProductName().equalsIgnoreCase("Fruits")){
            return false;
        }

        return false;
    }
}
