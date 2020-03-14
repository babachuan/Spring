package com.qhc.multi.beans;

public class Book {
    private int bookId;
    private String bookName;
    private int publisherId;
    //注意：这里是一个对象
    private Author author;
    private int price;

    public Book(int bookId, String bookName, int publisherId, Author author, int price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisherId = publisherId;
        this.author = author;
        this.price = price;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", publisherId=" + publisherId +
                ", author=" + author +
                ", price=" + price +
                '}';
    }
}
