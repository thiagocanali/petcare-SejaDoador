package br.com.care.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.care.model.TipoUser;
import br.com.care.repositories.TipoUserRepository;
import br.com.care.security.utils.UserToken;

@RestController
@RequestMapping("/tipo-user")
public class TipoUserResource {
	
	@Autowired
	TipoUserRepository tipoUserRepository;
	
	@GetMapping("/get-tipo/{token}")
	public Long getTipo(@PathVariable("token") String token) {
		long idUser = UserToken.parseToken(token).getId();
		
		return tipoUserRepository.findByIdUser(idUser).get().getFlgTipo();
	}
	
	@Transactional
	@GetMapping("/resetar-id")
	public ResponseEntity<?> resetIncrement() {
		tipoUserRepository.deleteAll();
		tipoUserRepository.resetIncrementID();
		
		if(tipoUserRepository.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Auto increment resetado!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Erro ao resetar auto increment");
		}
	}
	
	
	@PostMapping("/salvar-tipo")
	public long salvarTipo(@RequestBody TipoUser tipoUser) {
		TipoUser tipoSalvo = tipoUserRepository.save(tipoUser);
		
		if(tipoSalvo.getId() != 0) {
			return tipoSalvo.getId();
		} else {
			return -1;
		}
	}
	
	@DeleteMapping("/deletar-tipo/{id}")
	public String deletarTipo(@PathVariable("id") long idTipo) {
		if(tipoUserRepository.findById(idTipo).isPresent()) {
			tipoUserRepository.deleteById(idTipo);
			if(!tipoUserRepository.findById(idTipo).isPresent()) {
				return "OK";
			} else {
				return "Deletado tipo de ID " + idTipo;
			}
		} else {
			return "Não localizado tipo de ID " + idTipo;
		}
	}

}
