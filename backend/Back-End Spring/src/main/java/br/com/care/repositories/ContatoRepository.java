package br.com.care.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.care.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	
	List<Contato> findAllByIdEmpresa(long id);
	List<Contato> findAllByIdDono(long id); 
	List<Contato> findAllByIdCaregiver(long id);
	Optional<Contato> findByTelefone(String telefone);
	
	@Override
	@SQLUpdate(sql = "DELETE FROM CONTATO WHERE ID != 0;")
	void deleteAll();
	
	@Modifying
	@Query(value = "alter table CONTATO auto_increment = 1;", nativeQuery = true)
	void resetIncrementID();

}
