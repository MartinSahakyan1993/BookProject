package storage;

import model.Author;
import model.User;

public class UserStorage {
    User[] array = new User[10];
    private int size = 0;

    public void add(User user) {
        if (size == array.length - 1) {
            extend();
        }
        array[size++] = user;
    }

    private void extend() {
        User[] array2 = new User[(array.length * 3) / 2 + 1];
        for (int i = 0; i < array.length; i++) {
            array2[i] = array[i];
        }
        array = array2;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ". " + array[i] + " ");
            System.out.println();
        }

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


    public int getSize() {
        return size;
    }


    public User getUserByIndex(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        return array[index];
    }

    public void addAuthor(Author authorByIndex) {

    }

    public User getUserByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }

}
