package com.microservice.webservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//@RequestMapping(method=RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello world bean", "i am message2");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world, %s", name));
	}
	/*
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning", null, locale);
	}
	*/
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}
}
