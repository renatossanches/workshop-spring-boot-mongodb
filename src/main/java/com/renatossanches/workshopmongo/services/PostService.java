package com.renatossanches.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatossanches.workshopmongo.domain.Post;
import com.renatossanches.workshopmongo.repository.PostRepository;
import com.renatossanches.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
	    Optional<Post> postOpt = repo.findById(id);
	    return postOpt.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
}
