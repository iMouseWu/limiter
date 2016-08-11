# limiter
[![Build Status](https://travis-ci.org/iMouseWu/limiter.svg?branch=master)](https://travis-ci.org/iMouseWu/limiter)

#### 包括三个模块
1. limiter-lock 锁
2. limiter-tokenbucket 令牌桶模块
3. limiter-open-platform 开放平台限流器模块

#### 简单说明
1. 可以从appkey,method,appkey+detail对开放平台接口调用进行限制,默认后者的配置会覆盖前者
2. 整个项目现在还是基于单机进行限流,如果需要实现集群限流可以自己实现相应的接口的集群实现

#### 使用说明
1. 配置文件参数说明
    * `capacity: `容量,即初始给每个限制的访问次数.(`appkey`的`capacity=200`表示该`appkey`可以在一瞬间访问200次)
    * `addNum: `每addTimeWithMillisecond`单位时间内增加的访问次数(`addNum=1;addTimeWithMillisecond=2000`表示2S增加1次访问次数,访问次数不能超过`capacity`)
    * `addTimeWithMillisecond: `每addTimeWithMillisecond`单位时间内增加`addNum`访问次数,单位毫秒
    * `addPeriod: `增加访问次数的周期,单位毫秒,如果addPeriod不填写后者addPeriod小于addTimeWithMillisecond则默认使用`addTimeWithMillisecond`(`addPeriod=3000`表示距离上次次数增加的时间超过3S,才会进行下一轮的访问次数的增加,增加数量规则由`addNum`和`addTimeWithMillisecond`决定)

2. `LimiterFacade`是该项目的总入口,默认锁,数据存储,配置读取现在默认的都是存在本地,框架是对方开放式,所以有需求的可以自己实现接口
    * `ParseService`,xml解析器,用于解析配置文件
    * `RuleDao`,规则Dao,返回整个xml配置信息
    * `ConfigCenter`,令牌桶配置管理中心
    * `ConfigLoad`,令牌桶配置加载,将xml里面的规则转换成令牌桶的配置信息
    * `LockService`,锁服务,因为对令牌桶的令牌操作涉及到数据竞争,所以需要加锁(当然如果可以利用CAS的,`LockService`可以用空实现替换)
    * `TokenBucketDao`,令牌桶信息的存储
    * `TokenFilledStrategy`,令牌桶填充个策略,当前策略是如果如果达到令牌桶的容量则停止

3. 运行方式
        LimiterFacade limiterFacade = new LimiterFacade();
        limiterFacade.consume();
        limiterFacade.tryConsume();