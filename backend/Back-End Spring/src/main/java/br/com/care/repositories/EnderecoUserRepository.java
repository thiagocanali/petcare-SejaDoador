package br.com.care.repositories;

import java.util.List;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.care.model.EnderecoUser;

@Repository
public interface EnderecoUserRepository extends JpaRepository<EnderecoUser, Long>{
	
	List<EnderecoUser> findAllByIdEmpresa(long idEmpresa);
	List<EnderecoUser> findAllByIdDono(long idDono);
	List<EnderecoUser> findAllByIdCaregiver(long idCaregiver);
	
	@Override
	@SQLUpdate(sql = "DELETE FROM ENDERECO_USER WHERE ID != 0;")
	void deleteAll();
	
	@Modifying
	@Query(value = "alter table ENDERECO_USER auto_increment = 1;", nativeQuery = true)
	void resetIncrementID();
}
