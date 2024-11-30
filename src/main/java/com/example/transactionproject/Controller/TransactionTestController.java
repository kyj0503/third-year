package com.example.transactionproject.Controller;

import com.example.transactionproject.Service.TransactionTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction-test")
@RequiredArgsConstructor
public class TransactionTestController {
    private final TransactionTestService transactionTestService;

    @PostMapping("/without")
    public void testWithoutTransaction() {
        transactionTestService.insertWithoutTransaction();
    }

    @PostMapping("/with")
    public void testWithTransaction() {
        transactionTestService.insertWithTransaction();
    }
}
