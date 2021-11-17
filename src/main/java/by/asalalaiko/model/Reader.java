package by.asalalaiko.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.concurrent.Exchanger;

public class Reader extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(Reader.class);

    private Integer readerName;


    private List<Book> bookList;
    private Book book;
    private Library library;
    private ReadingRoom readingRoom;
    private Exchanger<Book> exchanger;



    public Reader(Integer readerName, Library library, ReadingRoom readingRoom, Exchanger<Book> exchanger) {
        this.readerName = readerName;
        this.library = library;
        this.readingRoom = readingRoom;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        try {
            Book bookL = library.getBook();
            LOG.info("Rider {} take Book {} in Library", readerName, bookL.getId());
            returnBookToLib(bookL);

            Book bookRR = readingRoom.getBook();
            LOG.info("Rider {} take Book {} in Reading room", readerName, bookRR.getId());

            changeBook(bookRR);

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
        readingRoom.setBook(book);
    }

    private void  changeBook(Book book){
        try{
            book = exchanger.exchange(book);

            LOG.info("===============> Rider {} exchange to Book {} in Reading room", readerName, book.getId());

        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
