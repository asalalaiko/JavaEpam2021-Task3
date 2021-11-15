package by.asalalaiko.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class Reader extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(Reader.class);

    private Integer readerName;


    List<Book> bookList;
    Book book;
    Library library;
    ReadingRoom readingRoom;



    public Reader(Integer readerName, Library library) {
        this.readerName = readerName;
        this.library = library;
    }

    @Override
    public void run() {

        try {
            Book book = library.getBook();
            LOG.info("Rider {} take Book {}", readerName, book.getId());

            returnBook(book);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void returnBook(Book book) throws InterruptedException {
        sleep((long) (Math.random() * 300));
        LOG.info("Rider {} return Book {}", readerName, book.getId());
       library.setBook(book);

    }
}
