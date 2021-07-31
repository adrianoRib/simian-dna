package br.com.meli.simiandna.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class RedirectController {
	
	@RequestMapping("/")
	public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
    }
}
