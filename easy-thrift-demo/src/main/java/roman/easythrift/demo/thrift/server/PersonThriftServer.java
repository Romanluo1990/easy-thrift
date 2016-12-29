package roman.easythrift.demo.thrift.server;


import roman.easythrift.demo.server.AbstractThriftServer;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import roman.easythrift.demo.domain.Person;
import roman.easythrift.demo.service.PersonService;
import roman.easythrift.demo.thrift.generated.TPerson;
import roman.easythrift.demo.thrift.generated.TPersonThriftServer;


/**
 * Created by roman.luo on 2016/11/15.
 */
public class PersonThriftServer extends AbstractThriftServer implements TPersonThriftServer.Iface{

    @Autowired
    private PersonService personService;

    public PersonThriftServer(String serverName,String host, int port) {
        super(serverName, host, port);
    }

    /**
     * 模板方法，参照着写
     * @return
     */
    @Override
    protected TProcessor createProcessor() {
        return new TPersonThriftServer.Processor<TPersonThriftServer.Iface>(this);
    }

    @Override
    public TPerson queryById(String id) throws TException {
        Person person = personService.queryById(id);
        TPerson tPerson = new TPerson();
        BeanUtils.copyProperties(person,tPerson);
        return tPerson;
    }
}
