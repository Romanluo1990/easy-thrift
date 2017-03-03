package roman.easythrift.server;


import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.net.InetSocketAddress;

/**
 * Thrift抽象服务
 * Created by roman.luo on 2016/11/15.
 */
public abstract class AbstractThriftServer<T> {

    private final static String  THRIFT_IFACE_SIGN = "$Iface",
            THRIFT_PROCESSOR_SIGN = "$Processor";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ApplicationContext applicationContext;

    private String serverName;
    private String host;
    private int port;

    public AbstractThriftServer() {
    }

    public AbstractThriftServer(String serverName, String host, int port) {
        this.serverName = serverName;
        this.host = host;
        this.port = port;
    }

    private void startServer() throws Exception{
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        Class processorClazz = (Class) parameterizedType.getActualTypeArguments()[0];
        String processorClassName = processorClazz.getTypeName();
        Class ifaceClazz =  getClass().getClassLoader().loadClass(processorClassName+THRIFT_IFACE_SIGN);
        Constructor constructor = getClass().getClassLoader().loadClass(processorClassName+THRIFT_PROCESSOR_SIGN).getDeclaredConstructor(ifaceClazz);
        TProcessor tProcessor = (TProcessor)constructor.newInstance(applicationContext.getBean(this.getClass()));
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        TServerTransport serverTransport = new TServerSocket(socketAddress);
        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(tProcessor));
        new Thread(() -> {
            server.serve();
        }).start();
    }

    @PostConstruct
    public void start()  {
        try {
            startServer();
            log.info(serverName + "->" +host + ":" + port +" start success...");
        } catch (Exception e) {
            log.error(serverName + "->" +host + ":" + port + " start fail...");
            log.error(e.getMessage(), e);
        }
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
