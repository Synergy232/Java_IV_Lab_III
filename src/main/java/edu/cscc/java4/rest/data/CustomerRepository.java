package edu.cscc.java4.rest.data;

import edu.cscc.java4.rest.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
