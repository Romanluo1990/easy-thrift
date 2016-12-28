package roman.easythrift.service.impl;

import roman.easythrift.domain.Person;
import roman.easythrift.service.PersonService;

import roman.easythrift.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by roman.luo on 2016/11/17.
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDao personDao;

    @Override
    public Person queryById(String id) {
        return personDao.queryById(id);
    }
}
