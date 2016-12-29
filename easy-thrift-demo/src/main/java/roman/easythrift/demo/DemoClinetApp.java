package roman.easythrift.demo;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import roman.easythrift.demo.thrift.clinet.PersonThriftClient;
import roman.easythrift.demo.thrift.generated.TPerson;

/**
 * Created by roman.luo on 2016/11/15.
 */
public class DemoClinetApp {

    private static final Logger log = LoggerFactory.getLogger(DemoClinetApp.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:clinet-application.xml");
        try {
            TPerson person = classPathXmlApplicationContext.getBean(PersonThriftClient.class).queryById("1");
            System.out.println(person.getName());
        } catch (TException e) {
            e.printStackTrace();
        }
    }

}
