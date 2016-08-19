package operators;

import java.util.Iterator;
import operators.comparison.Equals;
import operators.comparison.GreaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import operators.structure.Row;
import operators.structure.Table;

public class RestrictTest {
    private Table sailors;
    private String[] sailorsAtts = new String[] {"sid", "sname", "rating", "age"};
    
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
    }
   
    @Test
    public void testBasic1(){
        Restrict restrict = new Restrict(sailors, new Equals(), "sid", 22);
        
        Iterator it = restrict.iterator();
        
        assertTrue(it.hasNext());
        
        Row row = (Row)it.next();
        
        assertEquals(row.getValue("sid"), 22);
        assertEquals(row.getValue("sname"), "Dustin");
        assertEquals(row.getValue("rating"), 7);
        assertEquals(row.getValue("age"), 45);
        
        assertFalse(it.hasNext());        
    }
    
    @Test
    public void testBasic2(){
        Restrict restrict = new Restrict(sailors, new Equals(), "sname", "Horatio");
        
        Iterator<Row> it = restrict.iterator();
        
        assertTrue(it.hasNext());
        
        // pega o primeiro 'Horatio'
        Row row = it.next();
        
        assertTrue(row.getValue("sid").equals(64) || row.getValue("sid").equals(74));
        assertEquals(row.getValue("sname"), "Horatio");
        assertTrue(row.getValue("rating").equals(7) || row.getValue("rating").equals(9));
        assertEquals(row.getValue("age"), 35);
        
        assertTrue(it.hasNext());
        
        // pega o segundo 'Horatio'
        row = (Row) it.next();
        
        assertTrue(row.getValue("sid").equals(64) || row.getValue("sid").equals(74));
        assertEquals(row.getValue("sname"), "Horatio");
        assertTrue(row.getValue("rating").equals(7) || row.getValue("rating").equals(9));
        assertEquals(row.getValue("age"), 35);               
        
        assertFalse(it.hasNext());        
    }
    
       
    @Test
    public void testBasic3(){
        Restrict restrict = new Restrict(sailors, new GreaterThan(), "age", 60);
        
        Iterator<Row> it = restrict.iterator();
        
        assertTrue(it.hasNext());
        
        Row row = (Row) it.next();
        
        assertEquals(row.getValue("sid"), 95);
        assertEquals(row.getValue("sname"), "Bob");
        assertEquals(row.getValue("rating"), 3);
        assertEquals(row.getValue("age"), 63);
        
        assertFalse(it.hasNext());        
    }
 }
