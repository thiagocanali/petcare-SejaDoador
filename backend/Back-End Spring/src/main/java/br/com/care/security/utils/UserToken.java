package br.com.care.security.utils;

import br.com.care.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserToken {
	
	public static Usuario parseToken(String token) {
		try {
			Claims body = Jwts.parser()
					.setSigningKey("$PnGHlAecYpZq$")
					.parseClaimsJws(token)
					.getBody();
			
			Usuario usuario = new Usuario();
			usuario.setemail(body.getSubject());
			usuario.setId(Long.parseLong((String) body.get("usuarioId")));
			
			return usuario;
		} catch (ClassCastException e) {
			return null;
		}
	}
	
	public static String generateToken(Usuario usuario) {
		Claims claims = Jwts.claims().setSubject(usuario.getemail());
		claims.put("usuarioId", usuario.getId() + "");
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, "$PnGHlAecYpZq$")
				.compact();
	}

}
