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



    public Reader(Integer readerName, Library library, ReadingRoom readingRoom) {
        this.readerName = readerName;
        this.library = library;
        this.readingRoom = readingRoom;
    }

    @Override
    public void run() {

        try {
            Book bookL = library.getBook();
            LOG.info("Rider {} take Book {} in Library", readerName, bookL.getId());
            returnBookToLib(bookL);

            Book bookRR = readingRoom.getBook();
            LOG.info("Rider {} take Book {} in Reading room", readerName, bookRR.getId());
            returnBookToRR(bookRR);



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void returnBookToLib(Book book) throws InterruptedException {
        sleep((long) (Math.random() * 300));
        LOG.info("Rider {} return Book {} to Library", readerName, book.getId());
        library.setBook(book);

    }

    private void returnBookToRR(Book book) throws InterruptedException {
        sleep((long) (Math.random() * 100));
        LOG.info("Rider {} return Book {} to Reading room", readerName, book.getId());
        library.setBook(book);

    }
}
