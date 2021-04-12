package com.javafun.resiliense4j.Resiliense4jSpringBoot2;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {

    private RestTemplate restTemplate;

    @PostMapping("/check")
    @CircuitBreaker(name = "inventoryBreak", fallbackMethod = "inventoryFallback")
    @ResponseBody
    public String checkOrder(@RequestBody Inventory  inventory){

        System.out.println("Inside check order, for product " + inventory.productName);
      ResponseEntity<Boolean> result = restTemplate
              .exchange(
                      new RequestEntity(inventory,
                              HttpMethod.GET,
                              URI.create("http://localhost:9090/inventory/check")),
                              Boolean.class);

      if(result.getBody()){
          return "Order placed Sucessufully";
      }

      return "Order Cannot Be placed";

    }

    public void checkOrderFallback(Inventory inventory, Throwable t ){
        System.out.println("Inside fallback, for product " + inventory.productName);
    }
}


