package by.asalalaiko.model;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class ReadingRoom {


    private Integer limitBooks;

    private List<Book> bookList;
    private Semaphore semaphore;
    private ReentrantLock lock = new ReentrantLock();

    public ReadingRoom(Integer limitBooks, List<Book> bookList) {
        this.limitBooks = limitBooks;
        this.bookList = bookList;
        semaphore = new Semaphore(bookList.size());
    }


    public Book getBook() throws InterruptedException {
        semaphore.acquire();
        lock.lock();
        Book freeBook = bookList.stream().filter(book -> book.isFree()).findFirst().get();
        freeBook.busy();

        lock.unlock();
        sleep((long) (Math.random() * 900));
        return freeBook;
    }

    public void setBook(Book book) throws InterruptedException {
        lock.lock();
        book.release();
        semaphore.release();
        lock.unlock();


    }
}
