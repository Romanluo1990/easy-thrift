package roman.easythrift.demo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import roman.easythrift.demo.dao.PersonDao;
import roman.easythrift.demo.dao.sql.PersonDaoSql;
import roman.easythrift.demo.domain.Person;

/**
 * Created by roman.luo on 2016/11/17.
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private PersonDaoSql personDaoSql;

    @Override
    public Person queryById(String id) {
        return personDaoSql.queryById(id);
    }
}
