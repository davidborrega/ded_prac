package uoc.ds.pr.util;

import edu.uoc.ds.adt.sequential.DictionaryArrayImpl;
import edu.uoc.ds.adt.sequential.FiniteContainer;
import uoc.ds.pr.SportEvents4Club;

import java.util.Comparator;

public class OrderedVectorDictionary<K,V> extends DictionaryArrayImpl<K,V> implements FiniteContainer<V> {

    private static final int LIMIT_ELEMENTS = 256;
    private Comparator<K> comparator;

    public OrderedVectorDictionary(Comparator<K> comparator) {
        this(LIMIT_ELEMENTS, comparator);
    }
    public OrderedVectorDictionary(int limitElements, Comparator<K> comparator) {
        super(limitElements);
        this.comparator = comparator;
    }
    
}
