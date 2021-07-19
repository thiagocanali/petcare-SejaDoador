package br.com.care.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.care.model.Dono;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Long>{

	Optional<Dono> findByRg(String rg);
	Optional<Dono> findByIdUser(long idUser);
	
	@Override
	@Query(value = "SELECT * FROM DONO WHERE ID != 0", nativeQuery = true)
	List<Dono> findAll();
	
	@Override
	@SQLUpdate(sql = "DELETE FROM DONO WHERE ID != 0;")
	void deleteAll();
	
	@Modifying
	@Query(value = "alter table DONO auto_increment = 1;", nativeQuery = true)
	void resetIncrementID();
}
