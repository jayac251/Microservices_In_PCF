/**
 * 
 */
package io.jc.sales.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 197651
 *
 */
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

}
