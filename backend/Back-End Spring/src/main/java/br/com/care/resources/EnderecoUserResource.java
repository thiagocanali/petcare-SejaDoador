package br.com.care.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.care.model.EnderecoUser;
import br.com.care.repositories.EnderecoUserRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoUserResource {
	
	@Autowired
	EnderecoUserRepository enderecoRepository;
	
	@GetMapping("/todos-enderecos/{tipoUsuario}/{id}")
	public List<EnderecoUser> getAllEnderecos(@PathVariable("tipoUsuario") int tipoUsuario, @PathVariable("id") long id) {
		if(tipoUsuario == 1) {
			return enderecoRepository.findAllByIdDono(id);
		} else if(tipoUsuario == 2) {
			return enderecoRepository.findAllByIdCaregiver(id);
		} else {
			return enderecoRepository.findAllByIdEmpresa(id);
		}
	}
	
	@Transactional
	@GetMapping("/resetar-id")
	public ResponseEntity<?> resetIncrement() {
		enderecoRepository.deleteAll();
		enderecoRepository.resetIncrementID();
		
		if(enderecoRepository.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Auto increment resetado!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Erro ao resetar auto increment");
		}
	}
	
	@GetMapping("/{id}")
	public EnderecoUser getEndereco(@Validated @PathVariable("id") long id) {
		Optional<EnderecoUser> enderecoEncontrado = enderecoRepository.findById(id);
		if(enderecoEncontrado.isPresent()) {
			return enderecoEncontrado.get();
		} else {
			return null;
		}
	}

	@Transactional
	@PostMapping("/salvar-endereco")
	public long salvarEndereco(@RequestBody EnderecoUser endereco) {
		EnderecoUser enderecoCadastrado = enderecoRepository.save(endereco);
		
		if(enderecoCadastrado.getID() != 0) {
			return enderecoCadastrado.getID();
		} else {
			return -1;
		}
	}
	
	@PostMapping("/editar-endereco/{id}")
	public String editarEndereco(@RequestBody EnderecoUser endereco, @PathVariable long idEndereco) {
		endereco.setID(idEndereco);
		
		Optional<EnderecoUser> enderecoBanco = enderecoRepository.findById(idEndereco); 
		
		if(enderecoBanco.isPresent()) {
			EnderecoUser enderecoEditado = enderecoRepository.save(endereco);
			if(enderecoEditado == enderecoBanco.get()) {
				return "OK";
			} else {
				return "Erro ao editar o cuidador do id " + idEndereco;
			}
		} else {
			return "O Cuidador de ID " + idEndereco + " não existe no banco de dados";
		}
	}
	
	@DeleteMapping("deletar/{id}") 
	public String deletarEndereco(@PathVariable long idEndereco) {
		if(enderecoRepository.findById(idEndereco).isPresent()) {
			enderecoRepository.deleteById(idEndereco);
			if(!enderecoRepository.findById(idEndereco).isPresent()) {
				return "OK";
			} else {
				return "Erro ao deletar o endereço de ID " + idEndereco;
			}
		} else {
			return "O Endereço de ID " + idEndereco + " não foi encontrado no banco";
		}
	}

}
