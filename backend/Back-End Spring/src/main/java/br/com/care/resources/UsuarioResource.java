package br.com.care.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.care.model.Usuario;
import br.com.care.repositories.UsuarioRepository;
import br.com.care.security.utils.UserToken;


@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UsuarioRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("verificarInfo/{tipo-info}")
	public boolean verificarInfos(@PathVariable("tipo-info") String tipo, @RequestParam("info") String info) {
		Optional<Usuario> user = Optional.ofNullable(new Usuario());

		switch (tipo) {
		case "cpf":
			user = userRepository.findByCpf(info);
			break;
		case "email":
			user = userRepository.findByEmail(info);
			break;
		case "cnpj":
			user = userRepository.findByCnpj(info);
			break;
		default:
			break;
		}

		if (user.isPresent() && user.get().getId() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping("/logar")
	public String login(@RequestParam("email") String email, @RequestParam("senha") String senha) {
		Optional<Usuario> usuario = userRepository.findByEmail(email);
		if(usuario.isPresent()) {
			if(passEncoder().matches(senha, usuario.get().getSenha())) {
				String token = UserToken.generateToken(usuario.get());
				return "{\"token\":\"" + token + "\"}";
			} else {
				return "{\"token\":\"" + -1 + "\"}";
			}
		}
		return "{\"token\":\"" + -1 + "\"}";
	}

	@CrossOrigin
	@PostMapping("/cadUsuario")
	public long create(@RequestBody Usuario usuario) {
		if(userRepository.findByEmail(usuario.getemail()).isEmpty()) {
			usuario.setSenha(encoder.encode(usuario.getSenha()));
			Usuario usuarioCadastrado = userRepository.save(usuario);

			if (usuarioCadastrado.getId() != 0) {
				return usuarioCadastrado.getId();
			} else {
				return -1;
			}
		}
		return -1;
	}

	@Transactional
	@GetMapping("/resetar-id")
	public ResponseEntity<?> resetIncrement() {
		userRepository.deleteAll();
		userRepository.resetIncrementID();

		if (userRepository.findAll().isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Auto increment resetado!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Erro ao resetar auto increment");
		}
	}

	@GetMapping("/get/{token}")
	public Usuario getUsuario(@PathVariable("token") String token) {
		long idUsuario = UserToken.parseToken(token).getId();
		return userRepository.findById(idUsuario).get();
	}

	@PostMapping("/editar-usuario/{id}")
	public String editarUsuario(@RequestBody Usuario usuario, @PathVariable("id") long idUsuario) {
		usuario.setId(idUsuario);

		Optional<Usuario> enderecoBanco = userRepository.findById(idUsuario);

		if (enderecoBanco.isPresent()) {
			Usuario enderecoEditado = userRepository.save(usuario);
			if (enderecoEditado == enderecoBanco.get()) {
				return "OK";
			} else {
				return "Erro ao editar o cuidador do id " + idUsuario;
			}
		} else {
			return "O Cuidador de ID " + idUsuario + " não existe no banco de dados";
		}
	}

	@DeleteMapping("del-usuario/{id}")
	public String delUsuario(@PathVariable("id") long id) {
		userRepository.deleteById(id);
		Optional<Usuario> verificarDel = userRepository.findById(id);

		if (!verificarDel.isPresent()) {
			return "Deletado com Sucesso!";
		} else {
			return "Erro ao deletar!";
		}
	}

}
