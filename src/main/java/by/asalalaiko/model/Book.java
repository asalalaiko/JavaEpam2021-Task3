package by.asalalaiko.model;

import java.util.concurrent.Semaphore;

public class Book {

    private Integer Id;
    private boolean free = true;
    private Semaphore semaphore;

    public Book(Integer id) {
        Id = id;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public boolean isFree() {
        return free;
    }

    public void busy() {
        free = false;
    }

    public void release() {
        free = true;

    }

    public Integer getId(){
        return Id;
    }
}
