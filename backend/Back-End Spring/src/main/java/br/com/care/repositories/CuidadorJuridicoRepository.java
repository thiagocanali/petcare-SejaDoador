package br.com.care.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.care.model.CuidadorJuridico;

@Repository
public interface CuidadorJuridicoRepository extends JpaRepository<CuidadorJuridico, Long>{

	Optional<CuidadorJuridico> findByIdUser(long id);
	
	@Override
	@Query(value = "SELECT * FROM CUIDADOR_JURIDICO WHERE ID != 0", nativeQuery = true)
	List<CuidadorJuridico> findAll();
	
	@Override
	@SQLUpdate(sql = "DELETE FROM CUIDADOR_JURIDICO WHERE ID != 0;")
	void deleteAll();
	
	@Modifying
	@Query(value = "ALTER TABLE CUIDADOR_JURIDICO AUTO_INCREMENT = 1;", nativeQuery = true)
	void resetIncrementID();
	
}
