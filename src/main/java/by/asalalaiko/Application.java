package by.asalalaiko;


import by.asalalaiko.model.Book;
import by.asalalaiko.model.Library;
import by.asalalaiko.model.Reader;
import by.asalalaiko.model.ReadingRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Salalaiko Alex
 * @my.task 3. Потоки.
 *	Маленькая библиотека.
 *	В библиотеке для чтения доступно определенное количество книг.
 *	Общее количество книг на руках у читателя и тех, что он взял в читальный зал,
 *	не должно превышать заданного числа.
 *	Читатели в читальном зале могут передать книги друг другу самостоятельно.
 *
 * @since 15.11.21
 */


public class Application {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args){



        List<Book> booksL = booksFromLibrary();
        Library library = new Library(2, booksL);

        List<Book> booksRR = booksFromReadingRoom();
        ReadingRoom readingRoom = new ReadingRoom(1, booksRR);

        List<Reader> readers = getReaders(library);

        LOG.info("Main thread started");
        readers.forEach(Thread::start);
        LOG.info("Main thread finished");
    }

    private static List<Reader> getReaders(Library library) {
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader(1, library));
        readers.add(new Reader(2, library));
        readers.add(new Reader(3, library));
        readers.add(new Reader(4, library));
        readers.add(new Reader(5, library));
        readers.add(new Reader(6, library));
        readers.add(new Reader(7, library));
        readers.add(new Reader(8, library));
        readers.add(new Reader(9, library));

        return readers;
    }


    private static List<Book> booksFromLibrary() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1));
        books.add(new Book(2));
        books.add(new Book(3));
        books.add(new Book(4));
//        books.add(new Book(5));
//        books.add(new Book(6));
//        books.add(new Book(7));
//        books.add(new Book(8));
        return books;
    }

    private static List<Book> booksFromReadingRoom() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(5));
        books.add(new Book(6));
        books.add(new Book(7));
        return books;
    }
}
