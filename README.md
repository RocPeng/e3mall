# e3mall
基于dubbo的分布式网上商城
项目架构:  
![](http://ompeujaa0.bkt.clouddn.com/dubbo/WX20180522-215356@2x.png)

本项目是基于dubbo的分布式商城系统  
dubbo简介:  
Dubbo是Alibaba开源的分布式服务框架，它最大的特点是按照分层的方式来架构，使用这种方式可以使各个层之间解耦合（或者最大限度地松耦合）。从服务模型的角度来看，Dubbo采用的是一种非常简单的模型，要么是提供方提供服务，要么是消费方消费服务，所以基于这一点可以抽象出服务提供方（Provider）和服务消费方（Consumer）两个角色。关于注册中心、协议支持、服务监控等内容。  
在以上的架构图中，e3-content-service相当于是服务的提供方，e3-manager-web是服务的消费方，e3-sso-service是服务的提供方，e3-sso-web是服务的消费方.  
Dubbo的总体架构，如图所示：   
## Dubbo背景和简介  
Dubbo开始于电商系统，因此在这里先从电商系统的演变讲起。

### 1.单一应用框架(ORM) 
当网站流量很小时，只需一个应用，将所有功能如下单支付等都部署在一起，以减少部署节点和成本。 
缺点：单一的系统架构，使得在开发过程中，占用的资源越来越多，而且随着流量的增加越来越难以维护  
![](https://img-blog.csdn.net/20170417183808108?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbm9hbWFuX3dncw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)  
### 2.垂直应用框架(MVC) 
垂直应用架构解决了单一应用架构所面临的扩容问题，流量能够分散到各个子系统当中，且系统的体积可控，一定程度上降低了开发人员之间协同以及维护的成本，提升了开发效率。 
缺点：但是在垂直架构中相同逻辑代码需要不断的复制，不能复用。
![](https://img-blog.csdn.net/20170417183837616?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbm9hbWFuX3dncw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)  
### 3.分布式应用架构(RPC) 
当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心  
![](https://img-blog.csdn.net/20170417184005890?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbm9hbWFuX3dncw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)  
### 4.流动计算架构(SOA) 
随着服务化的进一步发展，服务越来越多，服务之间的调用和依赖关系也越来越复杂，诞生了面向服务的架构体系(SOA)，也因此衍生出了一系列相应的技术，如对服务提供、服务调用、连接处理、通信协议、序列化方式、服务发现、服务路由、日志输出等行为进行封装的服务框架  
![](https://img-blog.csdn.net/20170417184119640?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbm9hbWFuX3dncw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 
 单一应用架构

当网站流量很小时，只需一个应用，将所有功能都部署在一起，以减少部署节点和成本。
此时，用于简化增删改查工作量的 数据访问框架(ORM) 是关键。
垂直应用架构

当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，将应用拆成互不相干的几个应用，以提升效率。
此时，用于加速前端页面开发的 Web框架(MVC) 是关键。
分布式服务架构 
当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。
此时，用于提高业务复用及整合的 分布式服务框架(RPC) 是关键。
流动计算架构 
当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。
此时，用于提高机器利用率的 资源调度和治理中心(SOA) 是关键。  
## Dubbo是什么
Dubbo是：

一款分布式服务框架
高性能和透明化的RPC远程服务调用方案
SOA服务治理方案
每天为2千多个服务提供大于30亿次访问量支持，并被广泛应用于阿里巴巴集团的各成员站点以及别的公司的业务中。  
### Dubbo架构  
![](https://img-blog.csdn.net/20170417185019149?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbm9hbWFuX3dncw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)  
Provider: 暴露服务的服务提供方。 
Consumer: 调用远程服务的服务消费方。 
Registry: 服务注册与发现的注册中心。 
Monitor: 统计服务的调用次数和调用时间的监控中心。

### 调用流程 
0.服务容器负责启动，加载，运行服务提供者。   
1.服务提供者在启动时，向注册中心注册自己提供的服务。   
2.服务消费者在启动时，向注册中心订阅自己所需的服务。   
3.注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。   
4.服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。   
5.服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心  

### Dubbo注册中心
对于服务提供方，它需要发布服务，而且由于应用系统的复杂性，服务的数量、类型也不断膨胀； 
对于服务消费方，它最关心如何获取到它所需要的服务，而面对复杂的应用系统，需要管理大量的服务调用。 
而且，对于服务提供方和服务消费方来说，他们还有可能兼具这两种角色，即既需要提供服务，有需要消费服务。

通过将服务统一管理起来，可以有效地优化内部应用对服务发布/使用的流程和管理。服务注册中心可以通过特定协议来完成服务对外的统一。  
#### Dubbo提供的注册中心有如下几种类型可供选择：

Multicast注册中心
Zookeeper注册中心
Redis注册中心
Simple注册中心  
### Dubbo优缺点  
优点：

透明化的远程方法调用 
- 像调用本地方法一样调用远程方法；只需简单配置，没有任何API侵入。
软负载均衡及容错机制 
可在内网替代nginx lvs等硬件负载均衡器。
服务注册中心自动注册 & 配置管理 
-不需要写死服务提供者地址，注册中心基于接口名自动查询提供者ip。 
使用类似zookeeper等分布式协调服务作为服务注册中心，可以将绝大部分项目配置移入zookeeper集群。
服务接口监控与治理 
-Dubbo-admin与Dubbo-monitor提供了完善的服务接口管理与监控功能，针对不同应用的不同接口，可以进行 多版本，多协议，多注册中心管理。
缺点：

只支持JAVA语言  

### 论文推荐:  
http://xueshu.baidu.com/s?wd=paperuri%3A%28eb0855e4aaa9d2bee9ef14baca3f0e46%29&filter=sc_long_sign&sc_ks_para=q%3D%E5%9F%BA%E4%BA%8ESOA%E6%9E%B6%E6%9E%84%E7%9A%84%E7%BD%91%E4%B8%8A%E5%95%86%E5%9F%8E%E7%9A%84%E8%AE%BE%E8%AE%A1%E4%B8%8E%E5%AE%9E%E7%8E%B0&sc_us=18426617378235330724&tn=SE_baiduxueshu_c1gjeupa&ie=utf-8  
http://xueshu.baidu.com/s?wd=paperuri%3A%28a6da4bc9dd9bd432d89a57c41c2debba%29&filter=sc_long_sign&tn=SE_xueshusource_2kduw22v&sc_vurl=http%3A%2F%2Fkns.cnki.net%2FKCMS%2Fdetail%2Fdetail.aspx%3Ffilename%3Drjdk201605006%26dbname%3DCJFD%26dbcode%3DCJFQ&ie=utf-8&sc_us=12210887665149124919  
http://xueshu.baidu.com/s?wd=paperuri%3A%28f9dd280cfabd799cbe8a8ac536a57ad3%29&filter=sc_long_sign&tn=SE_xueshusource_2kduw22v&sc_vurl=http%3A%2F%2Fwww.cqvip.com%2FQK%2F97354X%2F201701%2F671133103.html&ie=utf-8&sc_us=9329097767697314323  
### 技术博客推荐:   
https://blog.csdn.net/xxssyyyyssxx/article/details/72834243  
所有的博客:https://so.csdn.net/so/search/s.do?p=2&q=%E5%95%86%E5%9F%8E&t=blog&domain=&o=&s=&u=xxssyyyyssxx&l=&f=&rbg=0  
