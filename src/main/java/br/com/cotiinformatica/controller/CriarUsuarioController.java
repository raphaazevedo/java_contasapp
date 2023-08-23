package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.MD5Helper;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller

public class CriarUsuarioController {

	@Autowired // auto inicialização como variavel declarada
	UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/criar-usuario") // caminho da pasta no navegador
	public ModelAndView criarConta() {

		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		return modelAndView;
	}

	// capturar o post
	@RequestMapping(value = "/criar-usuario-post", method = RequestMethod.POST)
	public ModelAndView criarUsuarioPost(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("criar-usuario");

		try {
			Usuario usuario = new Usuario();
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(MD5Helper.encrypt(request.getParameter("senha")));

			// verifica se o email já foi cadastrado
			if (usuarioRepository.find(usuario.getEmail()) != null) {

				modelAndView.addObject("mensagem_erro", "O email informado já foi cadastrado!");

			} else {

				usuarioRepository.create(usuario);

				modelAndView.addObject("mensagem_sucesso", "Usuario cadastrado com sucesso!");
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Erro: " + e.getMessage());
		}

		return modelAndView;
	}
}
