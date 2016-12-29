package roman.easythrift.demo.thrift.clinet;


import org.apache.thrift.TException;
import roman.easythrift.demo.client.AbstractThriftClient;
import roman.easythrift.demo.thrift.generated.TPersonThriftServer;
import roman.easythrift.demo.thrift.generated.TPerson;


/**
 * Created by roman.luo on 2016/11/15.
 */
public class PersonThriftClient extends AbstractThriftClient<TPersonThriftServer.Client> implements TPersonThriftServer.Iface{

    public PersonThriftClient(String host, int port) {
        super(host, port);
    }

    public PersonThriftClient(String host, int port ,int timeout) {
        super(host, port, timeout);
    }

    /**
     * 不需要实现具体方法，方法留空
     * @param id
     * @return
     * @throws TException
     */
    @Override
    public TPerson queryById(String id) throws TException {
        //do nothing
        return null;
    }
}
