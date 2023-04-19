package it.aulab.springbootcontroller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import it.aulab.springbootcontroller.model.Author;
import it.aulab.springbootcontroller.model.Comment;
import it.aulab.springbootcontroller.model.Post;
import it.aulab.springbootcontroller.repository.AuthorRepository;
import it.aulab.springbootcontroller.repository.CommentRepository;
import it.aulab.springbootcontroller.repository.PostRepository;
import it.aulab.springbootcontroller.service.AuthorService;
import it.aulab.springbootcontroller.service.AuthorServiceImpl;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
// @DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// con spring boot test autoconfigure non vale più quindi aggiunge i dati al database
class SpringBootControllerApplicationTests {
	
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PostRepository postRepository;
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	
	AuthorService authorService;
	
	
		@BeforeEach
	
	public void createData() {
		Author a1 = new Author();
		a1.setFirstName("Mirko");
		a1.setLastName("Abbrescia");
		a1.setEmail("mirkoabbrescia@aulab.it");
	

		authorRepository.save(a1);

		Author a2 = new Author();
		a2.setFirstName("Andrea");
		a2.setLastName("Mininni");
		a2.setEmail("andreamininni@aulab.it");

		authorRepository.save(a2);

		Post p1 = new Post();
		p1.setAuthor(a1);
		p1.setTitle("Il nuovo Pixel 7 Pro");
		p1.setBody("Lorem ipsum");
		p1.setPublishDate("20230411");

		postRepository.save(p1);

		Post p2 = new Post();
		p2.setAuthor(a1);
		p2.setTitle("Il nuovo IPhone 14");
		p2.setBody("Lorem ipsum");
		p2.setPublishDate("20230411");

		postRepository.save(p2);

		Comment c1 = new Comment();
		c1.setEmail("mirkoabbrescia@aulab.it");
		c1.setPost(p1);
		c1.setBody("Non mi è piace");
		c1.setPublishDate("20230411");

		Comment c2 = new Comment();
		c2.setEmail("mirkoabbrescia@aulab.it");
		c2.setPost(p1);
		c2.setBody("Bellissimo, consigliato");
		c1.setPublishDate("20230412");

		Comment c3 = new Comment();
		c3.setEmail("mirkoabbrescia@aulab.it");
		c3.setPost(p2);
		c3.setBody("Bellissimo, consigliato");
		c1.setPublishDate("20230412");


		List<Comment> commentList = new ArrayList<Comment>();
		commentList.add(c1);
		commentList.add(c2);
		commentList.add(c3);

		commentRepository.saveAll(commentList);
		// mettiamo tutti i commenti in una lista e utilizziano saveAll per salvare la lista intera senza salvare ogni singolo oggetto


		// commentRepository.save(c1);
		// commentRepository.save(c2);


		// gli oggetti li creiamo tramite le repository e il metodo save utilizza in automatico entitymanager


		

	}
	@Test
	void customQuery1() {
	
		List<Post> posts = postRepository.findMirko();

		assertThat(posts).hasSize(2);
		assertThat(posts.get(0))
				.extracting("author")
				.extracting("firstName")
				.isEqualTo("Mirko");


		// lista di post assegnati a mirko
		// prendi il/i post assegnati estraine l'autore estraine il firstname dell'autore controlla se che sia uguale a mirko
		
	}
	@Test
	void customQuery2() {

		Author a = new Author();
		a.setFirstName("Andrea");
		a.setLastName("Ciccio");
		a.setEmail("andreaciccio@aulab.it");

		authorRepository.save(a);

		Post p = new Post();
		p.setAuthor(a);
		p.setTitle("Che bella giornata");
		p.setBody("Lorem ipsum");
		p.setPublishDate("20230411");

		postRepository.save(p);

		List<Post> posts = postRepository.findByAuthorFirstName("Andrea");

		assertThat(posts).hasSize(1);
		// trova un solo elemento perchè controlla i post associati ad un autore con il nome andrea
		assertThat(posts)
				.extracting("author")
				.extracting("firstName")
				.contains("Andrea", "Andrea");
				// metto il doppio andrea nel contains perhcè ho due volori corrispondenti alla ricerca
			
	}

	@Test
	void customQuery3() {
	
		List<Post> posts = postRepository.findByAuthorFirstNameAndLastName("Abbrescia", "Mirko");

		assertThat(posts).hasSize(2);
		assertThat(posts)
				.first()
				.extracting("author")
				.extracting("firstName")
				.isEqualTo("Mirko");

		assertThat(posts.get(0))
				.extracting("author")
				.extracting("lastName")
				.isEqualTo("Abbrescia");
	}

	@Test
	void customQuery4() {
		assertThat(commentRepository.count()).isEqualTo(3);

	
		commentRepository.deleteMirko();

		assertThat(commentRepository.count()).isEqualTo(0);
	}

	
	
	


	@Test
	void testTransaction(){
		try{

			authorService.noTransaction();
		}catch (Exception e){

			
			assertThat(authorRepository.findByFirstNameAndLastName(null, null)).hasSize(0);
		}
	}

}
