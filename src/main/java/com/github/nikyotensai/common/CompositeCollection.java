package com.github.nikyotensai.common;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class CompositeCollection<E> extends AbstractCollection<E> {

    private LinkedList<Iterable<E>> list = new LinkedList<>();

    private int size = 0;

    public CompositeCollection(Iterable<E> iterable) {
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

        public boolean hasNext() {
            Iterator<Iterable<E>> iterator = list.iterator();
            while (true) {
                current = iterator.next();
                if (current == null) {
                    return false;
                }

                if (iterator.hasNext()) {
                    return true;
                }
            }
        }

        public E next() {
            if (current == null) {
                return null;
            }
            return current.iterator().next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super E> consumer) {

        }
    }
}
