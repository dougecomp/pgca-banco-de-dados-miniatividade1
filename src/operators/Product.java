package operators;

import java.util.Iterator;
import operators.structure.Row;
import operators.structure.Table;

public class Product implements Iterable<Row> {
    private Table tabela1;
    private Table tabela2;
    private String[] todosAtributos;
    
    public Product(Iterable sourceA, Iterable sourceB) {
        tabela1 = (Table) sourceA;
        tabela2 = (Table) sourceB;
        String[] atts = new String[tabela1.getAttributes().length + tabela2.getAttributes().length];
        System.arraycopy(tabela1.getAttributes(), 0, atts, 0, tabela1.getAttributes().length);
        System.arraycopy(tabela2.getAttributes(), 0, atts, tabela1.getAttributes().length, tabela2.getAttributes().length);
        todosAtributos = atts;
    }

    @Override
    public Iterator<Row> iterator() {
        return new MyIterator();
    }
    
    private class MyIterator implements Iterator<Row> {
        private Iterator<Row> tableIterator1;
        private Iterator<Row> tableIterator2;
        private Row nextRow;
        private Row pivo;
       
        public MyIterator() {
            tableIterator1 = tabela1.iterator();
            tableIterator2 = tabela2.iterator();
            pivo = tableIterator1.next();
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
            Row newRow = new Row(todosAtributos);
            
            while(pivo != null) {
                
                for(int i=0;i<pivo.getAttributes().length;i++) {
                    newRow.setValue(i, pivo.getValue(pivo.getAttributes()[i]));
                }
                while(tableIterator2.hasNext()) {
                    rowTable2 = tableIterator2.next();
                    for(int i=0;i<rowTable2.getAttributes().length;i++) {
                        newRow.setValue(i+pivo.getAttributes().length, rowTable2.getValue(rowTable2.getAttributes()[i]));
                    }
                    return newRow;
                    
                }
                tableIterator2 = tabela2.iterator();
                if(tableIterator1.hasNext()) {
                    pivo = tableIterator1.next();
                } else {
                    pivo = null;
                }
            }
        
            return null;
        }
    
    }
}
