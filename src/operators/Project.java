package operators;

import java.util.Iterator;
import operators.structure.Row;

public class Project implements Iterable<Row> {
    private Iterable table;
    private String[] columns;
    
    public Project(Iterable input, String... attributes) {
        this.table = input;
        this.columns = attributes;
    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>(){
            private Iterator<Row> tableIterator = table.iterator();
            
            @Override
            public boolean hasNext() {
                return tableIterator.hasNext();
            }

            @Override
            public Row next() {
                //Registro da tabela original
                Row originalRow = tableIterator.next();
                
                //Registro contendo apenas os atributos (colunas) de interesse
                Row newRow = new Row(columns); 
               
                //Copia os valores da coluna original para a nova
                for(int i=0; i<columns.length; i++){                    
                    newRow.setValue(i, originalRow.getValue(columns[i]));
                }
                
                return newRow;
            }                    
        };
    }
}
