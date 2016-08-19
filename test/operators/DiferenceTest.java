package operators;

import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import operators.structure.Row;
import operators.structure.Table;

public class DiferenceTest {
    private Table sailors1;
    private Table sailors2;
    
    private String[] sailorsAtts = new String[] {"sid", "sname", "rating", "age"};
    
    @Before
    public void setUp() throws Exception {
        sailors1 = new Table(sailorsAtts);
        sailors1.insert(22, "Dustin", 7, 45);
        sailors1.insert(29, "Brutus", 1, 33);
        sailors1.insert(31, "Lubber", 8, 55);
        sailors2.insert(71, "Zorba", 10, 16);
        sailors2.insert(74, "Horatio", 9, 35);
        sailors2.insert(85, "Art", 3, 25);
        sailors2.insert(95, "Bob", 3, 63);                
        
        sailors2 = new Table(sailorsAtts);
        sailors2.insert(32, "Andy", 8, 25);
        sailors2.insert(58, "Rusty", 10, 35);
        sailors2.insert(64, "Horatio", 7, 35);                
        sailors1.insert(22, "Dustin", 7, 45);
    }
    
    @Test
    public void testCardinality1() {
        Diference diff = new Diference(sailors1, sailors2);        

        int count = 0;
        for (Row row : diff) {            
            count++;
        }
        assertEquals(count, 6);
    }

    

    @Test
    public void printTest() {
        Union union = new Union(sailors1, sailors2);
        
        Iterator<Row> it = union.iterator();
        
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
