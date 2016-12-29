package roman.easythrift.demo.dao;

import roman.easythrift.demo.domain.Person;

/**
 * Created by roman.luo on 2016/11/17.
 */
public interface PersonDao {

    Person queryById(String id);

}
