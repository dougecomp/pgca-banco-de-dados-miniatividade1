
package operators.structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Arrays;

/**
 * Represents a Relational table (simplified )
 * @author João B. Rocha-Junior
 */

public class Table implements Iterable<Row> {

    private final String[] attributes;
    private final HashMap<String, Row> rows;

    //attributes of the table
    public Table(String[] attributes) {
        this.attributes = attributes;
        this.rows = new HashMap<>();
    }

    //returns attributes of the table
    public String[] getAttributes() {
        return attributes;
    }

    //insert values in the table, the number of values must be the same as the
    //number of attributes
    public void insert(Comparable... values) {
        if (values.length == attributes.length) {
            Row row = new Row(attributes);
            row.setValues(values);
            rows.put(Arrays.toString(row.getValues()), row);
        } else {
            throw new RuntimeException("The number of value must be equals the number of attributes.");
        }
    }

    //returns the cardinality of the table
    public int cardinality() {
        return rows.size();
    }

    //iteraotor over the rows of the table
    @Override
    public Iterator<Row> iterator() {
        return rows.values().iterator();
    }

    //returns a representation of the table in String
    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder();
        Row head = new Row(attributes);
        head.setValues((Comparable[]) attributes);
        txt.append(head.toString()).append("\n");

        for (Row row : rows.values()) {
            txt.append(row.toString()).append("\n");
        }
        txt.append("#rows = ").append(this.cardinality()).append('\n');
        return txt.toString();
    }
}
