package com.github.nikyotensai.common;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class CompositeCollection<E> extends AbstractCollection<E> {

    private ArrayList<Iterable<E>> list = new ArrayList<>();

    public CompositeCollection() {
    }

    public CompositeCollection(Iterable<E> iterable) {
        combine(iterable);
    }

    public CompositeCollection<E> combine(Iterable<E> iterable) {
        list.add(iterable);
        return this;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
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


    private class Itr implements Iterator<E> {

        private Iterable<E> current;

        private Iterator<E> curIterator;

        private int size = list.size();

        private int index = 0;

        private boolean indexChanged;

        public boolean hasNext() {
            while (true) {
                if (index < size) {
                    current = list.get(index);
                } else {
                    return false;
                }
                if (getCurIterator().hasNext()) {
                    return true;
                } else {
                    index++;
                    indexChanged = true;
                }
            }
        }

        public E next() {
            if (current == null) {
                return null;
            }
            Iterator<E> curIterator = getCurIterator();
            if (curIterator.hasNext()) {
                return curIterator.next();
            }
            return null;
        }

        private Iterator<E> getCurIterator() {
            if (curIterator == null || indexChanged) {
                curIterator = current.iterator();
                indexChanged = false;
            }
            return curIterator;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> consumer) {
            throw new UnsupportedOperationException();
        }
    }
}
