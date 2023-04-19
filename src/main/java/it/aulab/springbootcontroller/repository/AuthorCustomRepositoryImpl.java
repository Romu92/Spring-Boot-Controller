package it.aulab.springbootcontroller.repository;

import org.springframework.beans.factory.annotation.Autowired;

import it.aulab.springbootcontroller.model.Author;
import it.aulab.springbootcontroller.model.Post;
import jakarta.transaction.Transactional;

public class AuthorCustomRepositoryImpl implements AuthorCustomRepository {


    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    @Transactional
	public void Transaction() throws Exception{
		
				Author a = new Author();
				a.setFirstName(null);
				a.setLastName(null);
				a.setEmail(null);
	
				authorRepository.save(a);
	
	
				Post p1= new Post();
				p1.setAuthor(a);
				p1.setBody("lorem");
				p1.setTitle("lorem");
				p1.setPublishDate("20230414");
	
				postRepository.save(p1);
	
				Post p2= new Post();
				p2.setAuthor(a);
				p2.setBody("lorem 2");
			
				p2.setPublishDate("20230414");
	
				throw new Exception();
	

	}

    @Override
    public void noTransaction() throws Exception {
		
        Author a = new Author();
        a.setFirstName(null);
        a.setLastName(null);
        a.setEmail(null);

        authorRepository.save(a);


        Post p1= new Post();
        p1.setAuthor(a);
        p1.setBody("lorem");
        p1.setTitle("lorem");
        p1.setPublishDate("20230414");

        postRepository.save(p1);

        Post p2= new Post();
        p2.setAuthor(a);
        p2.setBody("lorem 2");
    
        p2.setPublishDate("20230414");

        

        throw new Exception();

}
    
}
