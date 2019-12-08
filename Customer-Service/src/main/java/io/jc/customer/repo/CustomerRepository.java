/**
 * 
 */
package io.jc.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 197651
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


	

}
