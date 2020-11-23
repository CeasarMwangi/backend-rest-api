package backendrestapi.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backendrestapi.models.Privilege;

/**
 * Created by CLLSDJACKT013 on 10/05/2018.
 */
@Repository
public interface  PrivilegeRepository extends CrudRepository<Privilege,Long> {
	Privilege findByName(String name);
}
