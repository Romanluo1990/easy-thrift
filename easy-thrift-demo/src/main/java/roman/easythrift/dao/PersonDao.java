package roman.easythrift.dao;

import roman.easythrift.domain.Person;

/**
 * Created by roman.luo on 2016/11/17.
 */
public interface PersonDao {

    Person queryById(String id);

}
