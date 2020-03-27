package br.com.caelum.contas.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO dao;
	
	@Autowired
	public ContaController(ContaDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("form")
	public String execute(){
		return "conta/form";
	}
	
	@RequestMapping("addContas")
	public String adicionar(@Valid Conta conta, BindingResult br){
		
		if(br.hasErrors()){
			return "conta/form";
		}
		
		dao.adiciona(conta);
		
		return "forward:listaContas";
	}
	
	@RequestMapping("listaContas")
	public ModelAndView lista(){
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", dao.lista());
		
		return mv;
	}
	
	@RequestMapping("exibirConta")
	public String exibir(Long id, Model model){
		model.addAttribute("conta", dao.buscaPorId(id));
		return "conta/exibe";
	}
	
	@RequestMapping("alteraConta")
	public String altera(Conta conta) {
	  dao.altera(conta);
	  return "redirect:listaContas";
	}
	
	@RequestMapping("removeConta")
	public String removeConta(Conta conta){
		dao.remove(conta);
		
		return "redirect:listaContas";
	}
	
	@RequestMapping("pagaConta")
	public void pagaConta(Conta conta, HttpServletResponse res){
		dao.paga(conta.getId());
		res.setStatus(200);
	}
	
}
