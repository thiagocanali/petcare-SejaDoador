package br.com.care.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import br.com.care.model.Contato;
import br.com.care.repositories.ContatoRepository;

@RestController
@RequestMapping("/contato")
public class ContatoResource {

	@Autowired
	ContatoRepository contatoRepository;
 
	@GetMapping("/lista-contatos/{tipo-usuario}/{id-usuario}")
	public ResponseEntity<?> getAllContatos(@PathVariable("tipo-usuario") long tipoUser,
			@PathVariable("id-usuario") long id) {
		List<Contato> listaContatos = new ArrayList<Contato>();
		if (tipoUser == 1) {
			listaContatos = contatoRepository.findAllByIdDono(id);
		} else if (tipoUser == 2) {
			listaContatos = contatoRepository.findAllByIdCaregiver(id);
		} else {
			listaContatos = contatoRepository.findAllByIdEmpresa(id);
		}
		
		if(!listaContatos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(listaContatos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
	}
	
	@Transactional
	@GetMapping("/resetar-id")
	public ResponseEntity<?> resetarId() {
		contatoRepository.deleteAll();
		contatoRepository.resetIncrementID();
		
		if(contatoRepository.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Auto increment resetado!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Erro ao resetar auto increment");
		}
	}
	
	@GetMapping("/verificar-uso/{numero}")
	public boolean verificarUso(@PathVariable("numero") String numero) {
		Optional<Contato> contato = contatoRepository.findByTelefone(numero);
		
		if(contato.isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping("/get-contato/{id}")
	public ResponseEntity<?> getContato(@PathVariable("id") long idContato) {
		Optional<Contato> contato = contatoRepository.findById(idContato);
		
		if(contato.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(contato);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found!");
		}
	}

	@PostMapping("/salva-contato")
	public ResponseEntity<?> salvaContato(@RequestBody Contato contato) {
		Contato contatoSalvo = contatoRepository.save(contato);

		if (contatoSalvo.getId() != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(contatoSalvo.getId());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(-1);
		}
	}
	
	@PostMapping("/editar-contato/{id}") 
	public ResponseEntity<?> editarContato(@RequestBody Contato contato, @PathVariable("id") long idContato) {
		contato.setId(idContato);
		
		Optional<Contato> contatoBanco = contatoRepository.findById(idContato); 
		
		if(contatoBanco.isPresent()) {
			Contato cuidadorEditado = contatoRepository.save(contato);
			if(cuidadorEditado == contatoBanco.get()) {
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Editado!");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Nao Encontrado!");
		}
	}
	
	@DeleteMapping("/deletar-contato/{id}")
	public ResponseEntity<?> deletarContato(@PathVariable("id") long idContato) {
		if(contatoRepository.findById(idContato).isPresent()) {
			contatoRepository.deleteById(idContato);
			if(!contatoRepository.findById(idContato).isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Deletado!");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Nao Encontrado!");
		}
	}
}
