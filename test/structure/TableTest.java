package structure;

import operators.structure.Table;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TableTest {

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
        sailors.insert(32, "Andy", 8, 25.5);
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
    public void testBasic() {
        assertEquals(10, sailors.cardinality());
        assertEquals(4, boats.cardinality());
        assertEquals(10, reserves.cardinality());
    }

    @Test
    public void printTest() {
        System.out.println(sailors);
        System.out.println(boats);
        System.out.println(reserves);
    }
}
