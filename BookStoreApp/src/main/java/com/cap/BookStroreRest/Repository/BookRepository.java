package com.cap.BookStroreRest.Repository;

import com.cap.BookStroreRest.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
