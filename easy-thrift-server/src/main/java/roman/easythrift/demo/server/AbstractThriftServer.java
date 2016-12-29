package roman.easythrift.demo.server;


import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * Thrift抽象服务
 * Created by roman.luo on 2016/11/15.
 */
public abstract class AbstractThriftServer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String serverName;
    private String host;
    private int port;

    public AbstractThriftServer(String serverName, String host, int port) {
        this.serverName = serverName;
        this.host = host;
        this.port = port;
    }

    private void startServer() throws Exception{
        TProcessor tProcessor = createProcessor();
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        TServerTransport serverTransport = new TServerSocket(socketAddress);
        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(tProcessor));
        server.serve();
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

    protected abstract TProcessor createProcessor();

    @PostConstruct
    public void start()  {
        new Thread(() -> {
            try {
                startServer();
                log.info(serverName + "->" +host + ":" + port +" start success...");
            } catch (Exception e) {
                log.error(serverName + "->" +host + ":" + port + " start fail...");
                log.error(e.getMessage(), e);
            }
        }).start();

    }
}
