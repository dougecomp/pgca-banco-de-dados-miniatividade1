/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operators;

import java.util.Iterator;
import operators.structure.Row;
import operators.structure.Table;


public class Diference implements Iterable<Row>{
    private Table tabela1;
    private Table tabela2;
    
    public Diference(Iterable sourceA, Iterable sourceB) {
        tabela1 = (Table) sourceA;
        tabela2 = (Table) sourceB;
    }

    @Override
    public Iterator<Row> iterator() {        
        return new MyIterator();
    }
    
    private class MyIterator implements Iterator<Row> {
        private Iterator<Row> tableIterator1;
        private Iterator<Row> tableIterator2;
        private Row nextRow;
       
        public MyIterator() {
            tableIterator1 = tabela1.iterator();
            tableIterator2 = tabela2.iterator();
            nextRow = getNextRow();
        }
        
        @Override
        public boolean hasNext() {
            return nextRow != null;
        }

        @Override
        public Row next() {
           Row temp = nextRow;
           nextRow = getNextRow();
           return temp;
        }
        
        private Row getNextRow() {
            Row rowTable1 = null;
            Row rowTable2 = null;
            
            while(tableIterator1.hasNext()) {
                
                rowTable1 = tableIterator1.next();
                while(tableIterator2.hasNext()) {
                    
                    rowTable2 = tableIterator2.next();
                    Boolean diferentes = false;
                    for (String attribute : rowTable1.getAttributes()) {
                        // Se existe alguma coluna com valo
                        if( !rowTable1.getValue(attribute).equals(rowTable2.getValue(attribute) )) {
                            diferentes = true;
                            break;
                        }
                    }
                    if(diferentes) {
                        return rowTable1;
                    }
                    
                }
                tableIterator2 = tabela2.iterator();
            }
        
            return null;
        }
    
    }
}    
    
    

