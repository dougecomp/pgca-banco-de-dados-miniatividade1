package operators.comparison;

/**
 * Checks if two values are equals
 * @author Jo�o B. Rocha-Junior
 */
public class Equals implements Comparison{

    @Override
    public boolean compare(Comparable A, Comparable B) {
        return A.compareTo(B)==0;
    }
    
}
