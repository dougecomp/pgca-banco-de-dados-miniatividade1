package operators;

import java.util.Iterator;
import operators.comparison.Comparison;
import operators.structure.Row;


public class Restrict implements Iterable<Row>{


    public Restrict(Iterable source, Comparison comparisonOp, String attribute, Comparable value) {
    }

    @Override
    public Iterator<Row> iterator() {        
    }
}
