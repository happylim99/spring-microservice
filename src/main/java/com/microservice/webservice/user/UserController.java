package com.microservice.webservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> list() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> show(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("Id - " + id);
		
		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).list());
		//linkTo(methodOn(this.getClass()).)
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> store(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable int id)
	{
		User user = service.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("Id - " + id);
		
	}

}
