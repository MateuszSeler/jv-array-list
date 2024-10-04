package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] arrayList;
    int size = 0;
    int emptyPlaces = 10;

    public ArrayList() {
        arrayList = (T[]) new Object[emptyPlaces];
    }

    public void enlarge() {
        emptyPlaces = (int) (size * 0.5);
        T[] temporaryEnlargedArrayList = (T[]) new Object[size + emptyPlaces];
        for (int i = 0; i < size; i++) {
            temporaryEnlargedArrayList[i] = arrayList[i];
        }
        arrayList = (T[]) new Object[size + emptyPlaces];
        for (int i = 0; i < size; i++) {
            arrayList[i] = temporaryEnlargedArrayList[i];
        }
    }

    @Override
    public void add(T value) {
        if (emptyPlaces == 0) {
            enlarge();
        }
        arrayList[size] = value;
        emptyPlaces--;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else if (index == size - 1) {
            set(value, size -1);
        } else {
            T[] temporaryArrayList = (T[]) new Object[size - index];
            for (int i = index; i < size; i++) {
                temporaryArrayList[i - index] = arrayList[i];
            }
            set(value, index);
            for (int i = size - 1; i > index; i--) {
                remove(i);
            }
            for (T element : temporaryArrayList) {
                add(element);
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else {
            return arrayList[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else {
            arrayList[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("given index does not exist");
        } else {
            T removedElement = arrayList[index];

            if (index != (size - 1)) {
                T[] temporaryArrayList = (T[]) new Object[size - index - 1];

                for (int i = index + 1; i < size; i++) {
                    temporaryArrayList[i - index - 1] = arrayList[i];
                }
                for (int i = 0; i < temporaryArrayList.length; i++) {
                    arrayList[index + i] = temporaryArrayList[i];
                }
            }
            arrayList[size - 1] = null;
            size--;
            emptyPlaces++;
            return removedElement;
        }
    }

    @Override
    public T remove(T element) {
        int index = -1;
        T removedElementByEquals = null;
        for (int i = 0; i < size; i++)
            if (element != null && arrayList[i].equals(element)) {
                index = i;
                removedElementByEquals = arrayList[i];
            }
        if (index == -1) {
            throw new NoSuchElementException("element does not exist");
        } else {
            remove(index);
        }
        return removedElementByEquals;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
