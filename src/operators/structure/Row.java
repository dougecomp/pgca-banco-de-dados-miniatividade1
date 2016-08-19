package operators.structure;

import java.util.Objects;

/**
 * Represents a row in a table (simplified )
 * @author João B. Rocha-Junior
 */

public class Row {

    private final String[] attributes;
    private Comparable[] values;

    //the attributes of the row
    public Row(String[] attributes) {
        this.attributes = attributes;
        this.values = new Comparable[attributes.length];
    }

    //returns the attributes of the row
    public String[] getAttributes(){
        return attributes;
    }
    
    //get the value of the FIRST attribute that match the parameter
    public Comparable getValue(String att) {
         for (int i = 0; i < attributes.length; i++) {
            if (attributes[i].equals(att)) {
                return getValue(i);
            }
        }
        return null;
    }
    
    //get the value of the attribute in the given position (index)
    public Comparable getValue(int index) {
        if (index >= 0 && index < attributes.length) {
            return values[index];
        } else {
            return null;
        }
    }

    //returns all the values in the row, one value per attribute
    public Comparable[] getValues() {
        return values;
    }

    //sets all the values in the row, one value per attribute
    public void setValues(Comparable... values) {
        this.values = values;
    }

    //set the value in the given position(index)
    public void setValue(int index, Comparable value) {
        values[index] = value;
    }

    //compares two rows, checking pairs of values
    @Override
    public boolean equals(Object other) {
        if (other instanceof Row) {
            for (int i=0;i<attributes.length;i++) {
                if (!this.getValue(i).equals(((Row) other).getValue(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        for (int i=0;i<attributes.length;i++) {
            hash = 31 * hash + Objects.hashCode(this.getValue(i));
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        for (int i=0;i<attributes.length;i++) {
            txt.append(getValue(i)).append("\t\t");
        }
        return txt.toString();
    }
}
