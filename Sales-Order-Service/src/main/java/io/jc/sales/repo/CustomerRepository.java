/**
 * 
 */
package io.jc.sales.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.jc.sales.repo.CustomerSOS;

/**
 * @author 197651
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerSOS, Long> {


	
}
