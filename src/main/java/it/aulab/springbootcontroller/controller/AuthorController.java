package it.aulab.springbootcontroller.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.aulab.springbootcontroller.model.Author;
import it.aulab.springbootcontroller.repository.AuthorRepository;
import it.aulab.springbootcontroller.service.AuthorService;

/*
 *
 * Create -> POST    X
 * Read   -> GET     X
 * Update -> PUT     X
 * Delete -> DELETE  X
 * 
 */

@Controller
@RequestMapping(value = "authors")
public class AuthorController {
    
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorsService;

    // rotta -> "/authors" GET
    // @RequestMapping(value = "", method = RequestMethod.GET)
  

    // cosi il getmapping funziona sia con uri authors che co uri authors?firstName=nome
    @GetMapping
    public @ResponseBody List<Author> getAll(
    @RequestParam(name ="firstName", required = false) String firstName,
    @RequestParam(name ="lastName", required = false) String lastName) {
       return authorsService.read(firstName, lastName);
        
    }

    // rotta -> "/authors" POST
    // @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping
    public @ResponseBody Author post(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    // rotta parametrica -> "/authors/:id" PUT
    // @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @PutMapping("{id}")
    public @ResponseBody Author put(@PathVariable("id") Long id, @RequestBody Author author) throws Exception {

        return authorsService.update(id, author);
    }
        // aggiungiamo trows exception per avvisare che la funzione può lanciare un'eccezione
        // Optional<Author> dbOptionalAuthor = authorRepository.findById(id);
        // // si mette optional perchè non si sa se l'oggetto sia presente o meno
        // if (dbOptionalAuthor.isPresent()) {
        //     Author dbAuthor = dbOptionalAuthor.get();
        //     dbAuthor.setFirstName(author.getFirstName());
        //     dbAuthor.setLastName(author.getLastName());
        //     dbAuthor.setEmail(author.getEmail());
        //     authorRepository.save(dbAuthor);
        //     return dbAuthor;
        // }
        // throw new Exception();
        // serve a lanciare un'eccezione 
    

    // rotta parametrica -> "/authors/:id" DELETE
    // @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public @ResponseBody String delete(@PathVariable("id") Long id) throws Exception {
        return authorsService.delete(id);
    }
}



