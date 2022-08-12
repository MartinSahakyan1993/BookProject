package storage;

import model.Book;
import model.Author;
public class BooksStorage {
    Book[] array = new Book[10];
    private int size = 0;

    public void add(Book book) {
        if (size == array.length) {
            extend();
        }
        array[size++] = book;
    }

    private void extend() {
        Book[] array2 = new Book[(array.length * 3) / 2 + 1];
        for (int i = 0; i < array.length; i++) {
            array2[i] = array[i];
        }
        array = array2;
    }

    public void printAllBooks() {
        if (size == 0) {
            System.out.println("There are no registered books");
        }else {
            for (int i = 0; i < size; i++) {

                System.out.print(i + ". " + array[i] + " ");
            }
        }

        System.out.println();
    }


    public void deleteByIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("invalid index");
        } else {
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];
            }
            size--;
        }
    }

    public void printBooksByAuthorname(String authorname) {
        for (int i = 0; i < size; i++) {
            if (array[i].getAuthor().getName().equals(authorname)) {
                System.out.println(array[i]);
            }

        }

    }

    public int getSize() {
        return size;
    }


    public Book getByIndex(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return array[index];
    }


    public void PrintBooksbyGenre(String genre) {
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().equals(genre)) {
                System.out.println(array[i]);
            }

        }

    }

    public void Printbooksbypricerange(int a, int b) {
        for (int i = 0; i < size; i++) {
            if (a < b) {
                if (array[i].getPrice() >= a && array[i].getPrice() <= b) {
                    System.out.println(array[i]);

                }
            } else if (array[i].getPrice() >= b && array[i].getPrice() <= a) {
                System.out.println(array[i]);

            }

        }

    }

    public Book getAuthorByIndex(int Index) {
        if (Index < 0 || Index > size) {
            return null;
        }
        return array[Index];
    }
}
