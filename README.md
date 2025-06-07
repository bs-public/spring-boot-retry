# Spring Boot Retry

**Retry Configuration**

- `retryFor`: Exceptions that will trigger a retry (e.g., HttpServerErrorException).
- `noRetryFor`: Exceptions that will not be retried (e.g., HttpClientErrorException).
- `maxAttempts`: Total number of attempts (initial + retries).
- `backoff`: Delay between retries, multiplier increases delay after each attempt.
