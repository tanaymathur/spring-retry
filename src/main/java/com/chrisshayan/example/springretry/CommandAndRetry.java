package com.chrisshayan.example.springretry;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class CommandAndRetry {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleRetryService.class);


    @CircuitBreaker(maxAttempts = 1, openTimeout = 10000)
    public void exec() throws TypeOneException {
        retryWhenException();
    }

    @Retryable(
            value = {TypeOneException.class},
            maxAttempts = 3, backoff = @Backoff(200))
    public void retryWhenException() throws TypeOneException {
        LOGGER.info("Retrying");
        throw new TypeOneException();
    }

    @Recover
    public void recover(Throwable t) throws Throwable {
        LOGGER.info("SampleRetryService.recover");
        throw t;
    }
}
