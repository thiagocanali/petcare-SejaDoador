package br.com.care.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.care.model.CuidadorFisico;

@Repository
public interface CuidadorFisicoRepository extends JpaRepository<CuidadorFisico, Long> {
	
	Optional<CuidadorFisico> findByIdUser(long id);
	
	@Override
	@Query(value = "SELECT * FROM CUIDADOR_FISICO WHERE ID != 0", nativeQuery = true)
	List<CuidadorFisico> findAll();
	
	@Override
	@SQLUpdate(sql = "DELETE FROM CUIDADOR_FISICO WHERE ID != 0;")
	void deleteAll();
	
	@Modifying
	@Query(value = "ALTER TABLE CUIDADOR_FISICO AUTO_INCREMENT = 1;", nativeQuery = true)
	void resetIncrementID();

}
