package operators.comparison;

/**
 * Checks if one value is greater than the other
 * @author João B. Rocha-Junior
 */
public class GreaterThan implements Comparison{

    //returns true, if the value of A is greater than the value of B
    @Override
    public boolean compare(Comparable A, Comparable B) {
        return A.compareTo(B)>0;
    }
    
}

