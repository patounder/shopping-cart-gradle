package com.globant.shoppingcart.controller;

import com.globant.shoppingcart.dto.CartDTO;
import com.globant.shoppingcart.messaging.Listener;
import com.globant.shoppingcart.messaging.Publisher;
import com.globant.shoppingcart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@Slf4j
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping(value = "/shopping-cart")
    public ResponseEntity<CartDTO> saveCart(@RequestBody CartDTO cartDTOToSave){
         shoppingCartService.save(cartDTOToSave);
         return ResponseEntity.ok(new CartDTO());
    }

    @GetMapping(value = "/shopping-cart/session-id/{sessionId}", produces = "application/json")
    public CartDTO getCartWithSession(@PathVariable String sessionId){
        return shoppingCartService.getCartWithSessionId(sessionId);
    }

    @GetMapping(value = "/shopping-cart", produces = "application/json")
    public ResponseEntity<List<CartDTO>> getCarts(){
        log.debug("request get all carts");
        List<CartDTO> allCartList = this.shoppingCartService.getAllCarts();
        return ResponseEntity.ok(allCartList);
    }
}
