package roman.easythrift.client;

import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.lang.reflect.ParameterizedType;

/**
 * Thrift抽象客户端，只可单层继承
 * Created by roman.luo on 2016/6.
 */
public class AbstractThriftClient<E extends TServiceClient> implements  ThriftClient<E> {

    private final static int DEFAULT_TIMEOUT = 5000;

    private String host;

    private int port;

    private int timeout;

    public AbstractThriftClient(String host, int port) {
        this(host,port,DEFAULT_TIMEOUT);
    }

    public AbstractThriftClient(String host, int port, int timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public E buildServiceClient() throws TException {
        try{
            ParameterizedType parameterizedType = (ParameterizedType)getClass().getGenericSuperclass();
            parameterizedType.getActualTypeArguments();
            Class c = (Class) parameterizedType.getActualTypeArguments()[0];
            TTransport transport = new TSocket(host, port,timeout);
            transport.open();
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            return (E) c.getConstructor(TProtocol.class).newInstance(protocol);
        }catch (Exception e){
            throw new TException(e);
        }
    }
}
