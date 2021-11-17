package by.asalalaiko.model;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Library {

    private Integer limitBooks;

    private List<Book> bookList;
    private Semaphore semaphore;
    private ReentrantLock lock = new ReentrantLock();

    public Library(Integer limitBooks, List<Book> bookList) {
        this.limitBooks = limitBooks;
        this.bookList = bookList;
        semaphore = new Semaphore(bookList.size());
        bookList.forEach(book -> book.setSemaphore(semaphore));
    }

    public Book getBook() throws InterruptedException {
        semaphore.acquire();
        lock.lock();
        Book freeBook = bookList.stream().filter(book -> book.isFree()).findFirst().get();
        freeBook.busy();

        lock.unlock();
        sleep((long) (Math.random() * 150));
        return freeBook;
    }

    public void setBook(Book book) throws InterruptedException {
        lock.lock();
        book.release();
        lock.unlock();
    }

    public Integer getLimitBooks(){
        return limitBooks;
    }

}
