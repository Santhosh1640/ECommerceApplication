package com.santhuuu.microservices.order_service.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(name= "inventory-service", url = "http://localhost:8082")
public interface InventoryClient {

//    @GetMapping("/api/inventory")
    //Instead of using Feign Client here, we used RestClient
    @GetExchange("/api/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam int quantity);

}
