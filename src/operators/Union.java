package operators;

import java.util.Iterator;
import operators.structure.Row;
import operators.structure.Table;

public class Union implements Iterable<Row>{
    private Table tabela1;
    private Table tabela2;

    public Union(Iterable sourceA, Iterable sourceB) {
        tabela1 = (Table) sourceA;
        tabela2 = (Table) sourceB;
    }
    
    @Override
    public Iterator<Row> iterator() {        
        return new MyIterator();
    }
    
    private class MyIterator implements Iterator<Row> {
        private Iterator<Row> tableIterator1 = tabela1.iterator();
        private Iterator<Row> tableIterator2 = tabela2.iterator();
       
        @Override
        public boolean hasNext() {
            return tableIterator1.hasNext() || tableIterator2.hasNext();
        }

        @Override
        public Row next() {
            Iterator <Row> iterator = getIterator();
            
            return iterator.next();
        }
        
        private Iterator<Row> getIterator() {
            if(tableIterator1.hasNext())
                return tableIterator1;
            else
                return tableIterator2;
        }
    
    }
}
