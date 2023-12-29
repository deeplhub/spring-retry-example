package com.xh.retry.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author H.Yang
 * @date 2023/12/29
 */
@RestController
@RequestMapping("/retry")

public class RetryController {

    @Resource
    private RetryService retryService;

    @GetMapping("/demo/")
    public void demo() {
        retryService.callback1();
    }
}
