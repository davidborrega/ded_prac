package uoc.ds.pr.util;

import edu.uoc.ds.adt.sequential.FiniteContainer;
import edu.uoc.ds.traversal.Iterator;
import edu.uoc.ds.traversal.IteratorArrayImpl;

import java.util.Comparator;

public class OrderedVector<E> implements FiniteContainer<E> {

    private E[] elements;
    private int numberOfElements;
    private Comparator<E> comparator;

    public OrderedVector(int max, Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[max];
        this.numberOfElements = 0;
    }

    @Override
    public boolean isFull() {
        return this.numberOfElements == this.elements.length;
    }

    @Override
    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    @Override
    public int size() {
        return this.numberOfElements;
    }

    @Override
    public Iterator<E> values() {
        return new IteratorArrayImpl<E>(this.elements, this.numberOfElements, 0);
    }
}
