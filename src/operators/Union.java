package operators;

import java.util.Iterator;
import operators.structure.Row;
import operators.structure.Table;

public class Union implements Iterable<Row>{
    private final Table tabela1;
    private final Table tabela2;

    public Union(Iterable sourceA, Iterable sourceB) {
        tabela1 = (Table) sourceA;
        tabela2 = (Table) sourceB;
    }
    
    @Override
    public Iterator<Row> iterator() {        
        return new MyIterator();
    }
    
    private class MyIterator implements Iterator<Row> {
        private final Iterator<Row> tableIterator1 = tabela1.iterator();
        private final Iterator<Row> tableIterator2 = tabela2.iterator();
       
        @Override
        public boolean hasNext() {
            // Se um dos iterators ainda houverem linhas, então existe próximo
            return tableIterator1.hasNext() || tableIterator2.hasNext();
        }

        @Override
        public Row next() {
            
            // Para saber o próximo basta saber em qual das tabelas está interando e retornar o próximo de uma das duas tabelas.
            Iterator <Row> iterator = getIterator();
            
            return iterator.next();
        }
        
        // Função para saber de qual das duas tabelas passadas deve recuperar o próximo
        private Iterator<Row> getIterator() {
            if(tableIterator1.hasNext())
                return tableIterator1;
            else
                return tableIterator2;
        }
    
    }
}
