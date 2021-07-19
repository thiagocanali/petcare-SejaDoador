package br.com.care.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.care.model.TipoUser;

@Repository
public interface TipoUserRepository extends JpaRepository<TipoUser, Long>{
	
	Optional<TipoUser> findByIdUser(long id);
	
	@Override
	@Query(value = "SELECT * FROM TIPO_USER WHERE ID != 0 ", nativeQuery = true)
	List<TipoUser> findAll();
	
	@Override
	@SQLUpdate(sql = "DELETE FROM TIPO_USER WHERE ID != 0;")
	void deleteAll();
	
	@Modifying
	@Query(value = "alter table TIPO_USER auto_increment = 1;", nativeQuery = true)
	void resetIncrementID();
}
