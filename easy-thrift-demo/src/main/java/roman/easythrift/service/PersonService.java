package roman.easythrift.service;

import roman.easythrift.domain.Person;

/**
 * Created by roman.luo on 2016/11/17.
 */
public interface PersonService {

    Person queryById(String id);

}
