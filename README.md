# limiter
[![Build Status](https://travis-ci.org/iMouseWu/limiter.svg?branch=master)](https://travis-ci.org/iMouseWu/limiter)
[![](https://jitpack.io/v/iMouseWu/limiter.svg)](https://jitpack.io/#iMouseWu/limiter)

#### 包括四个模块
1. `limiter-lock` 锁
2. `limiter-tokenbucket` 令牌桶模块
3. `limiter-open-platform` 开放平台限流器模块
4. `limiter-common` 公有的类库

#### 简单说明
1. 可以从`appkey`,`method`,`appkey+detail`对开放平台接口调用进行限制,默认后者的配置会覆盖前者
2. 整个项目即可以基于单机进行限流,也可以基于集群实现(默认使用`Redis`)

#### 使用说明
1. 配置文件参数说明
    * `capacity:`容量,即初始给每个桶(限制)的容量.(`appkey`的`capacity=200`表示该`appkey`可以在一瞬间访问200次)
    * `addNum:`每`addTimeWithMillisecond`单位时间内增加的访问次数(`addNum=1;addTimeWithMillisecond=2000`表示`2S`增加1次访问次数,访问次数不能超过`capacity`)
    * `addTimeWithMillisecond:`每`addTimeWithMillisecond`单位时间内增加`addNum`访问次数,单位毫秒
    * `addPeriod:`增加访问次数的周期,单位毫秒,如果`addPeriod`不填写或者`addPeriod`小于`addTimeWithMillisecond`则默认使用`addTimeWithMillisecond`(`addPeriod=3000`表示距离上次次数增加的时间超过3S,才会进行下一轮的访问次数的增加,增加数量规则由`addNum`和`addTimeWithMillisecond`决定)

2. `LimiterFacade`为总入口
    * `ParseService`,`xml`解析器,用于解析配置文件
    * `RuleDao`,规则`Dao`,返回整个`xml`配置信息
    * `ConfigCenter`,令牌桶配置管理中心
    * `ConfigLoad`,令牌桶配置加载,将xml里面的规则转换成令牌桶的配置信息
    * `LockService`,锁服务,因为对令牌桶的令牌操作涉及到数据竞争,所以需要加锁(当然如果可以利用`CAS`的,`LockService`可以用空实现替换)
    * `TokenBucketDao`,令牌桶信息的存储
    * `TokenFilledStrategy`,令牌桶填充个策略,当前策略是如果如果达到令牌桶的容量则停止

3. 运行方式

		LimiterFacade limiterFacade = new LimiterFacade();
		limiterFacade.visit("appkey","method");
4. `maven`依赖

		<repositories>
		  <repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		  </repository>
		</repositories>

		<dependency>
	      <groupId>com.github.iMouseWu.limiter</groupId>
	      <artifactId>limiter-open-platform</artifactId>
	       <version>1.0.1</version>
	    </dependency>

