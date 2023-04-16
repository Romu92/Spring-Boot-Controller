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
import org.springframework.web.bind.annotation.ResponseBody;

import it.aulab.springbootcontroller.model.Author;
import it.aulab.springbootcontroller.repository.AuthorRepository;

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

    // rotta -> "/authors" GET
    // @RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Author> getAll() {
        return authorRepository.findAll();
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
        Optional<Author> dbOptionalAuthor = authorRepository.findById(id);
        if (dbOptionalAuthor.isPresent()) {
            Author dbAuthor = dbOptionalAuthor.get();
            dbAuthor.setFirstName(author.getFirstName());
            dbAuthor.setLastName(author.getLastName());
            dbAuthor.setEmail(author.getEmail());
            authorRepository.save(dbAuthor);
            return dbAuthor;
        }
        throw new Exception();
    }

    // rotta parametrica -> "/authors/:id" DELETE
    // @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public @ResponseBody String delete(@PathVariable("id") Long id) {
        // Long id = Long.valueOf(id);
        authorRepository.deleteById(id);
        return "OK";
    }

}

