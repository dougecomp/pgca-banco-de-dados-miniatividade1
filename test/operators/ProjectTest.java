package operators;


import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import operators.structure.Row;
import operators.structure.Table;

public class ProjectTest {
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
        Project project = new Project(sailors, "sname");
        
        Iterator it = project.iterator();
        
        assertTrue(it.hasNext());
        
        Row row = (Row) it.next();
        
        assertEquals(row.getValue("sid"), null);
        assertTrue(row.getValue("sname") instanceof String);
        assertEquals(row.getValue("rating"), null);
        assertEquals(row.getValue("age"), null);
        
        assertTrue(it.hasNext());
    }
    
    @Test
    public void testBasic2(){
        Project project = new Project(sailors, "rating", "age");
        
        Iterator it = project.iterator();
        
        assertTrue(it.hasNext());
        
        Row row = (Row) it.next();
        
        assertEquals(row.getValue("sid"), null);
        assertEquals(row.getValue("sname"), null);        
        assertTrue((Integer)row.getValue("rating")>=1 || (Integer)row.getValue("rating")<=10);        
        assertTrue((Integer)row.getValue("age")>=16 ||(Integer)row.getValue("age")<=63);
        
        
        assertTrue(it.hasNext());
    }
        
    @Test
    public void printTest(){
        Project project = new Project(sailors, "sname");
                
        
        Table table = new Table(new String[]{"sname"});
        
        for(Row row : project){
            table.insert(row.getValues());
        }
        //print the new generated table with data of column "sname"
        System.out.println(table.toString());
    }
    
 }
