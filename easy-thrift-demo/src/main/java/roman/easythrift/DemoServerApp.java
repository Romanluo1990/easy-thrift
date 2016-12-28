package roman.easythrift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by roman.luo on 2016/11/15.
 */
public class DemoServerApp {

    private static final Logger log = LoggerFactory.getLogger(DemoServerApp.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:server-application.xml");
        log.info(" start success...");
        try {
            DataSource dataSource = classPathXmlApplicationContext.getBean(DataSource.class);
            dataSource.getConnection().prepareStatement("create table person(id int primary key, name varchar(255), age int)").execute();
            dataSource.getConnection().prepareStatement("insert into person VALUES (1,'Tom',18)").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
