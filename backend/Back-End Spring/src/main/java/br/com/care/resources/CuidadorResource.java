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

import br.com.care.model.CuidadorFisico;
import br.com.care.model.CuidadorJuridico;
import br.com.care.repositories.CuidadorFisicoRepository;
import br.com.care.repositories.CuidadorJuridicoRepository;
import br.com.care.security.utils.UserToken;

@RestController
@RequestMapping("/cuidador")
public class CuidadorResource {
	
	@Autowired
	CuidadorFisicoRepository cuidadorFisicoRepository;
	
	@Autowired
	CuidadorJuridicoRepository cuidadorJuridicoRepository;
	
	@GetMapping("/cuidadores/fisicos")
	public ResponseEntity<?> getCuidadoresFisicos() {
		List<CuidadorFisico> listaCuidadores = cuidadorFisicoRepository.findAll();
		
		if(!listaCuidadores.isEmpty()) {
			return ResponseEntity.ok().body(listaCuidadores);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
		}
	}
	
	@GetMapping("/cuidadores/juridicos")
	public ResponseEntity<?> getCuidadoresJuridicos() {
		List<CuidadorJuridico> listaCuidadores = cuidadorJuridicoRepository.findAll();
		
		if(!listaCuidadores.isEmpty()) {
			return ResponseEntity.ok().body(listaCuidadores);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
		}
	}
	
	@GetMapping("/fisico/{token}")
	public ResponseEntity<?> getCuidadorFisico(@Validated @PathVariable("token") String token) {
		long idUsuario = UserToken.parseToken(token).getId();
		Optional<CuidadorFisico> cuidador = cuidadorFisicoRepository.findByIdUser(idUsuario);
		
		if(cuidador.isPresent()) {
			return ResponseEntity.ok().body(cuidador.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
		}
	}
	
	@GetMapping("/juridico/{token}")
	public ResponseEntity<?> getCuidadorJuridico(@Validated @PathVariable("token") String token) {
		long idUsuario = UserToken.parseToken(token).getId();
		Optional<CuidadorJuridico> cuidador = cuidadorJuridicoRepository.findByIdUser(idUsuario);
		
		if(cuidador.isPresent()) {
			return ResponseEntity.ok().body(cuidador.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
		}
	}
	
	@Transactional
	@GetMapping("/fisico/reset-id")
	public ResponseEntity<?> resetIncrementFisico() {
		cuidadorFisicoRepository.deleteAll();
		cuidadorFisicoRepository.resetIncrementID();
		
		if(cuidadorFisicoRepository.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Auto increment resetado!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Erro ao resetar auto increment");
		}
	}
	
	@Transactional
	@GetMapping("/juridico/reset-id")
	public ResponseEntity<?> resetIncrementJuridico() {
		cuidadorJuridicoRepository.deleteAll();
		cuidadorJuridicoRepository.resetIncrementID();
		
		if(cuidadorJuridicoRepository.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Auto increment resetado!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Erro ao resetar auto increment");
		}
	}
	
	@PostMapping("/novo-cuidador/fisico")
	public ResponseEntity<?> cadastraCuidadorFisico(@RequestBody CuidadorFisico cuidador) {
		CuidadorFisico cuidadorCadastrado = cuidadorFisicoRepository.save(cuidador);
		
		if(cuidadorCadastrado.getId() != 0) {
			return ResponseEntity.status(HttpStatus.OK).body(cuidadorCadastrado.getId());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("-1");
		}
	}
	
	@PostMapping("/novo-cuidador/juridico")
	public ResponseEntity<?> cadastraCuidadorJuridico(@RequestBody CuidadorJuridico cuidador) {
		CuidadorJuridico cuidadorCadastrado = cuidadorJuridicoRepository.save(cuidador);
		
		if(cuidadorCadastrado.getId() != 0) { 
			return ResponseEntity.status(HttpStatus.OK).body(cuidadorCadastrado.getId());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("-1");
		}
	}
	
	@PostMapping("/editar-cuidador/fisico/{id}")
	public ResponseEntity<?> editarCuidadorFisico(@RequestBody CuidadorFisico cuidador, @PathVariable("id") long idCuidador) {
		cuidador.setId(idCuidador);
		
		Optional<CuidadorFisico> cuidadorBanco = cuidadorFisicoRepository.findById(idCuidador); 
		
		if(cuidadorBanco.isPresent()) {
			CuidadorFisico cuidadorEditado = cuidadorFisicoRepository.save(cuidador);
			if(cuidadorEditado == cuidadorBanco.get()) {
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERRO");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO - Not Found");
		}
	}
	
	@PostMapping("/editar-cuidador/juridico/{id}")
	public ResponseEntity<?> editarCuidadorJuridico(@RequestBody CuidadorJuridico cuidador, @PathVariable("id") long idCuidador) {
		cuidador.setId(idCuidador);
		
		Optional<CuidadorJuridico> cuidadorBanco = cuidadorJuridicoRepository.findById(idCuidador); 
		
		if(cuidadorBanco.isPresent()) {
			CuidadorJuridico cuidadorEditado = cuidadorJuridicoRepository.save(cuidador);
			if(cuidadorEditado == cuidadorBanco.get()) {
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERRO");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO - Not Found");
		}
	}
	
	@DeleteMapping("/deletar-cuidador/fisico/{id}")
	public ResponseEntity<?> deletarCuidadorFisico(@PathVariable("id") long idCuidador) {
		if(cuidadorFisicoRepository.findById(idCuidador).isPresent()) {
			cuidadorFisicoRepository.deleteById(idCuidador);
			if(!cuidadorFisicoRepository.findById(idCuidador).isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERRO");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO - Not Found");
		}
	}
	
	@DeleteMapping("/deletar-cuidador/juridico/{id}")
	public ResponseEntity<?> deletarCuidadorJuridico(@PathVariable("id") long idCuidador) {
		if(cuidadorJuridicoRepository.findById(idCuidador).isPresent()) {
			cuidadorJuridicoRepository.deleteById(idCuidador);
			if(!cuidadorJuridicoRepository.findById(idCuidador).isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body("OK");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERRO");
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO - Not Found");
		}
	}

}
