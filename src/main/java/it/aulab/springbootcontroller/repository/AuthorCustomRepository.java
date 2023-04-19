package it.aulab.springbootcontroller.repository;

public interface AuthorCustomRepository {
    void Transaction() throws Exception;
    void noTransaction() throws Exception;
}
