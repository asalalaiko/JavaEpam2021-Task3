package by.asalalaiko;


import by.asalalaiko.model.Book;
import by.asalalaiko.model.Library;
import by.asalalaiko.model.Reader;
import by.asalalaiko.model.ReadingRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

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
        ReadingRoom readingRoom = new ReadingRoom(booksRR);

        Exchanger<Book> exchanger = new Exchanger<>();

        List<Reader> readers = getReaders(library, readingRoom, exchanger);


        readers.forEach(Thread::start);

        LOG.info("Main thread finished");
    }

    private static List<Reader> getReaders(Library library, ReadingRoom readingRoom, Exchanger exchanger) {
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader(1, library, readingRoom, exchanger));
        readers.add(new Reader(2, library, readingRoom, exchanger));
        readers.add(new Reader(3, library, readingRoom, exchanger));
//        readers.add(new Reader(4, library, readingRoom, exchanger));
//        readers.add(new Reader(5, library, readingRoom, exchanger));
//        readers.add(new Reader(6, library, readingRoom, exchanger));
//        readers.add(new Reader(7, library, readingRoom, exchanger));
//        readers.add(new Reader(8, library, readingRoom, exchanger));
//        readers.add(new Reader(9, library, readingRoom, exchanger));

        return readers;
    }


    private static List<Book> booksFromLibrary() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1));
        books.add(new Book(2));
        books.add(new Book(3));
        books.add(new Book(4));
        books.add(new Book(5));
        books.add(new Book(6));
//        books.add(new Book(7));
//        books.add(new Book(8));
        return books;
    }

    private static List<Book> booksFromReadingRoom() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(10));
        books.add(new Book(11));
        books.add(new Book(12));
        books.add(new Book(13));
        books.add(new Book(14));
        books.add(new Book(15));
        return books;
    }
}
