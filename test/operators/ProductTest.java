/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators;

import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import operators.structure.Row;
import operators.structure.Table;

public class ProductTest {

    private Table sailors;
    private String[] sailorsAtts = new String[]{"sid", "sname", "rating", "age"};
    private Table boats;
    private String[] boatsAtts = new String[]{"bid", "bname", "color"};
    private Table reserves;
    private String[] reservesAtts = new String[]{"sid", "bid", "date"};

    @Before
    public void setUp() throws Exception {
        sailors = new Table(sailorsAtts);
        sailors.insert(22, "Dustin", 7, 45);
        sailors.insert(29, "Brutus", 1, 33);
        sailors.insert(31, "Lubber", 8, 55);
        sailors.insert(32, "Andy", 8, 25);
        sailors.insert(58, "Rusty", 10, 35);
        sailors.insert(64, "Horatio", 7, 35);
        sailors.insert(71, "Zorba", 10, 16);
        sailors.insert(74, "Horatio", 9, 35);
        sailors.insert(85, "Art", 3, 25);
        sailors.insert(95, "Bob", 3, 63);

        boats = new Table(boatsAtts);
        boats.insert(101, "Interlake", "blue");
        boats.insert(102, "Interlake", "red");
        boats.insert(103, "Cliper", "green");
        boats.insert(104, "Marine", "red");

        reserves = new Table(reservesAtts);
        reserves.insert(22, 101, "10/10/98");
        reserves.insert(22, 102, "10/10/98");
        reserves.insert(22, 103, "10/10/98");
        reserves.insert(22, 104, "10/7/98");
        reserves.insert(31, 102, "11/10/98");
        reserves.insert(31, 103, "11/6/98");
        reserves.insert(31, 104, "11/12/98");
        reserves.insert(64, 101, "9/5/98");
        reserves.insert(64, 102, "9/8/98");
        reserves.insert(74, 103, "9/8/98");
    }

    @Test
    public void testCardinality1() {
        Product product = new Product(sailors, boats);        

        int count = 0;
        for (Row row : product) {            
            count++;
        }
        assertEquals(count, 40);
    }

    @Test
    public void testAttributes() {
        Product product = new Product(sailors, boats);

        Iterator it = product.iterator();
        
        assertTrue(it.hasNext());

        Row row = (Row) it.next();
        
        assertEquals(sailors.getAttributes()[0], row.getAttributes()[0]); //sid
        assertEquals(sailors.getAttributes()[1], row.getAttributes()[1]);//sname
        assertEquals(sailors.getAttributes()[2], row.getAttributes()[2]);//rating
        assertEquals(sailors.getAttributes()[3], row.getAttributes()[3]);//age
        
        assertEquals(boats.getAttributes()[0], row.getAttributes()[4]);//bid
        assertEquals(boats.getAttributes()[1], row.getAttributes()[5]);//bname
        assertEquals(boats.getAttributes()[2], row.getAttributes()[6]);//color
        
    }

    
    @Test
    public void printTest() {
        Product product = new Product(sailors, reserves);
        
        Iterator<Row> it = product.iterator();
        
        if (it.hasNext()) {
            Row row = (Row) it.next();
            Table table = new Table(row.getAttributes());
            table.insert(row.getValues());

            while (it.hasNext()) {
                table.insert(it.next().getValues());
            }
            //print the new generated table
            System.out.println(table.toString());
        }

    }
}
