Spring Retry提供了自动重新调用失败的操作的功能。 在Spring Retry中，可以通过配置和注解来实现自动重试。

# 注解方式

## @Retryable

注解需要被重试的方法

* value ：需要进行重试的异常，和参数includes是一个意思。默认为空，当参数exclude也为空时，所有异常都将要求重试。
* include ：需要进行重试的异常，默认为空。当参数exclude也为空时，所有异常都将要求重试。
* exclude ：不需要重试的异常。默认为空，当参include也为空时，所有异常都将要求重试。
* stateful 标明重试是否是有状态的，异常引发事物失效的时候需要注意这个。该参数默认为false。远程方法调用的时候不需要设置，因为远程方法调用是没有事物的；只有当数据库更新操作的时候需要设置该值为true，特别是使用Hibernate的时候。抛出异常时，异常会往外抛，使事物回滚；重试的时候会启用一个新的有效的事物。
* maxAttempts ：最大重试次数，默认为3。包括第一次失败。
* backoff ：回避策略，默认为空。该参数为空时是，失败立即重试，重试的时候阻塞线程。
* exceptionExpression：SimpleRetryPolicy.canRetry()返回true时该表达式才会生效，触发重试机制。如果抛出多个异常，只会检查最后那个。表达式举例："message.contains('you can retry this')“ 并且"@someBean.shouldRetry(#root)“

> 注意：使用了@Retryable的方法里面不能使用try...catch包裹，要在发放上抛出异常，不然不会触发。

### @Backoff

重试回退策略（立即重试还是等待一会再重试）

* value：重试延迟时间，单位毫秒，默认值1000，即默认延迟1秒。当未设置multiplier时，表示每隔value的时间重试，直到重试次数到达maxAttempts设置的最大允许重试次数。当设置了multiplier参数时，该值作为幂运算的初始值。
* delay：等同value参数，两个参数设置一个即可。
* maxDelay：两次重试间最大间隔时间。当设置multiplier参数后，下次延迟时间根据是上次延迟时间乘以 multiplier得出的，这会导致两次重试间的延迟时间越来越长，该参数限制两次重试的最大间隔时间，当间隔时间大于该值时，计算出的间隔时间将会被忽略，使用上次的重试间隔时间。
* multiplier：作为乘数用于计算下次延迟时间。公式：delay = delay * multiplier
* random：是否启用随机退避策略，默认false。设置为true时启用退避策略，重试延迟时间将是delay和maxDelay间的一个随机数。设置该参数的目的是重试的时候避免同时发起重试请求，造成Ddos攻击。

### @Recover

用于方法。用于@Retryable失败时的“兜底”处理方法。

* 该注解用于恢复处理方法，当全部尝试都失败时执行。返回参数必须和@Retryable修饰的方法返回参数完全一样。第一个参数必须是异常，其他参数和@Retryable修饰的方法参数顺序一致。
* 要触发@Recover方法，那么在@Retryable方法上不能有返回值，只能是void才能生效。


<br>
<br>
<br>
<br>

**参考地址：**

* [Spring 重试指南](https://www.baeldung.com/spring-retry)
* [Spring Retry 详细教程](https://www.jianshu.com/p/702fd5f3adf2)
* [Spring Boot中使用Spring-Retry重试框架](https://blog.csdn.net/cckevincyh/article/details/112347200)