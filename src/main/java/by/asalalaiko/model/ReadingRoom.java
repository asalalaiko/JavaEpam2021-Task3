package by.asalalaiko.model;

import java.util.List;
import java.util.concurrent.Semaphore;

public class ReadingRoom {


    private Integer limitBooks;

    private List<Book> bookList;
    private Semaphore semaphore;

    public ReadingRoom(Integer limitBooks, List<Book> bookList) {
        this.limitBooks = limitBooks;
        this.bookList = bookList;
    }
}
