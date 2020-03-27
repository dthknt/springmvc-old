package br.com.caelum.contas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.modelo.Usuario;

@Controller
public class UsuarioController {
	
	@RequestMapping("login")
	public String form(){
		return "usuario/login";
	}
	
	@RequestMapping("logar")
	public String logar(Usuario usuario, HttpSession session){
		UsuarioDAO dao = new UsuarioDAO();
		if(dao.existeUsuario(usuario)){
			session.setAttribute("usuarioLogado", usuario);
			return "redirect:listaContas";
		}
		
		return "usuario/login";
	}
		
	@RequestMapping("logout")
	public String logout(Usuario usuario, HttpSession session){
		session.invalidate();
		return "usuario/login";
	}
	
}
