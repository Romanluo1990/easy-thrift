package roman.easythrift.demo.client;

import org.apache.thrift.TException;
import org.apache.thrift.TServiceClient;

/**
 * Created by roman.luo on 2016/6.
 */
public interface ThriftClient<E extends TServiceClient> {

    E buildServiceClient() throws TException;

}
