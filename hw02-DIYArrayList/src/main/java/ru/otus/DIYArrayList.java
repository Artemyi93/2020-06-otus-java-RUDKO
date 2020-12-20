package ru.otus;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class DIYArrayList<E> implements List<E> {

    protected transient int modCount = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
    transient Object[] elementData;
    private int size;

    public DIYArrayList() {
        elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        ++modCount;
        add(e, elementData, size);
        return true;
    }

    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity * 2;
        if (newCapacity == 0 || elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        } else {
            return elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private Object[] grow() {
        return this.grow(this.size + 1);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, this.size);
        return elementData(index);
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, this.size);
        E oldValue = elementData(index);
        this.elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        this.rangeCheckForAdd(index);
        ++modCount;
        int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }

        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elementData[index] = element;
        this.size = s + 1;

    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DIYArrayList.ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);
        return new DIYArrayList.ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    static <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }

    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;
        int expectedModCount;

        Itr() {
            expectedModCount = modCount;
        }

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            } else {
                Object[] elementData = DIYArrayList.this.elementData;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i + 1;
                    return (E) elementData[lastRet = i];
                }
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            int size = DIYArrayList.this.size;
            int i = cursor;
            if (i < size) {
                Object[] es = elementData;
                if (i >= es.length) {
                    throw new ConcurrentModificationException();
                }

                while (i < size && modCount == expectedModCount) {
                    action.accept(DIYArrayList.elementAt(es, i));
                    ++i;
                }

                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }

        }

        final void checkForComodification() {
            if (DIYArrayList.this.modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            } else {
                Object[] elementData = DIYArrayList.this.elementData;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    cursor = i;
                    return (E) elementData[lastRet = i];
                }
            }
        }

        public void set(E e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            } else {
                this.checkForComodification();

                try {
                    DIYArrayList.this.set(lastRet, e);
                } catch (IndexOutOfBoundsException var3) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        public void add(E e) {
            this.checkForComodification();

            try {
                int i = cursor;
                DIYArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = DIYArrayList.this.modCount;
            } catch (IndexOutOfBoundsException var3) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
