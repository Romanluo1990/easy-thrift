##easy-thrift是什么?
easy-thrift是利用spring的切面对thrift的简单封装，是的在spring中使用thrift更简洁

##easy-thrift结构

* easy-thrift-client 调用thrift的客户端的封装
* easy-thrift-server 提供thrift的服务端的封装
* easy-thrift-demo 一个简单的demo


##使用方法

* server端spring配置：
    ```xml
    <!--启动的thrift服务-->
    <bean class="roman.easythrift.thrift.server.PersonThriftServer">
        <constructor-arg name="host" value="${demo.person.host}"></constructor-arg>
        <constructor-arg name="port" value="${demo.person.port}"></constructor-arg>
        <constructor-arg name="serverName" value="personThriftServer"></constructor-arg>
    </bean>
    ```

* client端spring配置：
    ```xml
    <!--proxy-target-class="true"使用cglib代理 开启支持aspectj -->
    <aop:aspectj-autoproxy proxy-target-class="true"/>

    <!--使用ThriftClientAspect-->
    <bean id="thriftClientAspect" class="roman.easythrift.client.ThriftClientAspect">
    </bean>

    <!--使用服务的客户端-->
    <bean id="personThriftClient" class="roman.easythrift.thrift.clinet.PersonThriftClient">
        <constructor-arg name="host" value="${demo.person.host}"></constructor-arg>
        <constructor-arg name="port" value="${demo.person.port}"></constructor-arg>
    </bean>
    ```

##demo案例

* 启动服务端app：roman.easythrift.demo.DemoServerApp.main()

* 运行客户端app：roman.easythrift.demo.DemoClinetApp.main()
客户端输出结果：Tom



邮件(530827804#qq.com, 把#换成@)
QQ: 530827804
