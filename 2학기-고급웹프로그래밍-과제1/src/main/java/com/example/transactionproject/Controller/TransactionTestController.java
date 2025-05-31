package com.example.transactionproject.Controller;

import com.example.transactionproject.Entity.Post;
import com.example.transactionproject.Service.TransactionTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction-test")
@RequiredArgsConstructor
public class TransactionTestController {
    private final TransactionTestService transactionTestService;

    @PostMapping("/without")
    public void testWithoutTransaction(@RequestBody List<Post> postFormList) {
        postFormList.forEach(post -> post.setId(null)); // ID를 null로 설정하여 새로운 엔티티로 처리
        transactionTestService.insertWithoutTransaction(postFormList);
    }

    @PostMapping("/with")
    public void testWithTransaction(@RequestBody List<Post> postFormList) {
        postFormList.forEach(post -> post.setId(null)); // ID를 null로 설정하여 새로운 엔티티로 처리
        transactionTestService.insertWithTransaction(postFormList);
    }
}
