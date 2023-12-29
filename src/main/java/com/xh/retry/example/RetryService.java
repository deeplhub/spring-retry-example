package com.xh.retry.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author H.Yang
 * @date 2023/12/29
 */
@Slf4j
@Service
public class RetryService {

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000, multiplier = 5))
    public void callback1() {
        log.info("callback1...................");
        throw new RuntimeException("retry");
    }


    /**
     * 用于@Retryable失败时的“兜底”处理方法
     *
     * @param e
     */
    @Recover
    public void recover(RuntimeException e) {
        log.info("=>>>>>" + e.getMessage());
    }
}