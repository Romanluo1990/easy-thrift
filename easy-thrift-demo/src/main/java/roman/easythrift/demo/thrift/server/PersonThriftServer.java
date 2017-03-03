package roman.easythrift.demo.thrift.server;


import roman.easythrift.server.AbstractThriftServer;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import roman.easythrift.demo.domain.Person;
import roman.easythrift.demo.service.PersonService;
import roman.easythrift.demo.thrift.generated.TPerson;
import roman.easythrift.demo.thrift.generated.TPersonThriftServer;


/**
 * Created by roman.luo on 2016/11/15.
 */
public class PersonThriftServer extends AbstractThriftServer<TPersonThriftServer> implements TPersonThriftServer.Iface{

    @Autowired
    private PersonService personService;

    @Override
    public TPerson queryById(String id) throws TException {
        Person person = personService.queryById(id);
        TPerson tPerson = new TPerson();
        BeanUtils.copyProperties(person,tPerson);
        return tPerson;
    }
}
