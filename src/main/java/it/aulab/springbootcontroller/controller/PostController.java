package it.aulab.springbootcontroller.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.aulab.springbootcontroller.dto.PostDTO;
import it.aulab.springbootcontroller.model.Post;
import it.aulab.springbootcontroller.repository.PostRepository;

@Controller
@RequestMapping(value = "posts")
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;


    // @GetMapping
    // public @ResponseBody List<Post> getAll(){
    //     return postRepository.findAll();

    //     // con la circular regerence stampa su postman nella sezione author i dati dell'autore a cui è collegato il commento se mettessi la @jesonignore nella relazione con la tabella author nel model post allora non mi stamperebbe gli autori
    // }
    @GetMapping
    public @ResponseBody List<PostDTO> getAll(){
      List<PostDTO> dtos=new ArrayList<PostDTO>();

            for(Post p : postRepository.findAll()) {
                PostDTO map=modelMapper.map(p,PostDTO.class);
                dtos.add(map);
            }
      return dtos;
    }



}
