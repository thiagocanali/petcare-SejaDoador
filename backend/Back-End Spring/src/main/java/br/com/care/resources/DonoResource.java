package br.com.care.resources;

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

import br.com.care.model.Dono;
import br.com.care.repositories.DonoRepository;
import br.com.care.security.utils.UserToken;

@RestController
@RequestMapping("/dono")
public class DonoResource {
	
	@Autowired
	DonoRepository donoRepository;
	
	@GetMapping("/lista-donos")
	public ResponseEntity<?> listaDonos() {
		List<Dono> listaDonos = donoRepository.findAll();
		
		if(!listaDonos.isEmpty()) {
			return ResponseEntity.ok().body(listaDonos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
		}
	}
	
	@GetMapping("/verificar-rg/{rg}")
	public boolean verificarRG(@PathVariable("rg") String rg) {
		if(donoRepository.findByRg(rg).isPresent()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	@GetMapping("/resetar-id")
	public ResponseEntity<?> resetIncrement() {
		donoRepository.deleteAll();
		donoRepository.resetIncrementID();
		
		if(donoRepository.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Auto increment resetado!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Erro ao resetar auto increment");
		}
	}
	
	@GetMapping("/get-dono/{token}")
	public ResponseEntity<?> getDono(@PathVariable("token") String token) {
		long idUsuario = UserToken.parseToken(token).getId();
		Optional<Dono> dono = donoRepository.findByIdUser(idUsuario);
		
		if(dono.isPresent()) {
			return ResponseEntity.ok().body(dono);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
		}
	}
	
	@PostMapping("/editar-infos-dono/{id}")
	public ResponseEntity<?> editarDono(@RequestBody Dono dono, @PathVariable("id") long idDono) {
		dono.setId(idDono);
		
		Optional<Dono> donoBanco = donoRepository.findById(idDono);
		
		if(donoBanco.isPresent()) {
			Dono donoEditado = donoRepository.save(dono);
			if(donoEditado == donoBanco.get()) {
				return ResponseEntity.ok().body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO - Not Found");
		}
	}
	
	@PostMapping("/cad-infos-pessoais")
	public ResponseEntity<?> cadastrarInfos(@RequestBody Dono dono) {
		System.out.println(dono);
		Dono donoSalvo = donoRepository.save(dono);
		
		if(donoSalvo.getId() != 0) {
			return ResponseEntity.ok().body(donoSalvo.getId());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("-1");
		}
	}
	
	@DeleteMapping("/deletar-dono/{id}")
	public ResponseEntity<?> deletarDono(@PathVariable("id") long idDono) {
		if(donoRepository.findById(idDono).isPresent()) {
			donoRepository.deleteById(idDono);
			if(!donoRepository.findById(idDono).isPresent()) {
				return ResponseEntity.ok().body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO - Not Found");
		}
	}
}
