package roman.easythrift.demo.dao.sql;


import roman.easythrift.demo.domain.Person;

/**
 * Created by roman.luo on 2016/11/17.
 */
public interface PersonDaoSql {

    Person queryById(String id);

}
