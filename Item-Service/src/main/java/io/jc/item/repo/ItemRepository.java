/**
 * 
 */
package io.jc.item.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 197651
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


	

}
