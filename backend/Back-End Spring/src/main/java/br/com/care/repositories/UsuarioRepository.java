package br.com.care.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.care.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByCpf(String cpf);
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByCnpj(String cnpj);
	
	public boolean existsByEmail(String email);
	
	@Override
	@Query(value = "SELECT * FROM USUARIO WHERE ID != 0", nativeQuery = true)
	List<Usuario> findAll();
	
	@Override
	@SQLUpdate(sql = "DELETE FROM USUARIO WHERE ID != 0;")
	void deleteAll();
	
	@Modifying
	@Query(value = "alter table USUARIO auto_increment = 1;", nativeQuery = true)
	void resetIncrementID();
}
