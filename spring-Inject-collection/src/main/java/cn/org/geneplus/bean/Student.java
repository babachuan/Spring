package cn.org.geneplus.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 11:22
 */
public class Student {

    private String[] bookArrays;
    private Set<String> bookSet;
    private List<String> bookList;
    private Map<String,String> bookMap;
    private List<Book> bookObjList;


    public Student() {
    }

    public Student(String[] bookArrays, Set<String> bookSet, List<String> bookList, Map<String, String> bookMap, List<Book> bookObjList) {
        this.bookArrays = bookArrays;
        this.bookSet = bookSet;
        this.bookList = bookList;
        this.bookMap = bookMap;
        this.bookObjList = bookObjList;
    }

    public String[] getBookArrays() {
        return bookArrays;
    }

    public void setBookArrays(String[] bookArrays) {
        this.bookArrays = bookArrays;
    }

    public Set<String> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<String> bookSet) {
        this.bookSet = bookSet;
    }

    public List<String> getBookList() {
        return bookList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }

    public Map<String, String> getBookMap() {
        return bookMap;
    }

    public void setBookMap(Map<String, String> bookMap) {
        this.bookMap = bookMap;
    }

    public List<Book> getBookObjList() {
        return bookObjList;
    }

    public void setBookObjList(List<Book> bookObjList) {
        this.bookObjList = bookObjList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "bookArrays=" + Arrays.toString(bookArrays) +
                ", bookSet=" + bookSet +
                ", bookList=" + bookList +
                ", bookMap=" + bookMap +
                ", bookObjList=" + bookObjList +
                '}';
    }
}
