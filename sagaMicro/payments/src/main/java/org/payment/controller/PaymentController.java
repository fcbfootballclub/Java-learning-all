package org.payment.controller;

import org.payment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "payment")
public class PaymentController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }
}
