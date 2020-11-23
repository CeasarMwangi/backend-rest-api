package backendrestapi.repositories;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backendrestapi.models.Role;

/**
 * Created by CLLSDJACKT013 on 10/05/2018.
 */
@Repository
public interface  RoleRepository extends CrudRepository<Role,Long> {
	Role findByName(String name);
}
