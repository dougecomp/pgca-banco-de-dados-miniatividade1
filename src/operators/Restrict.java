package operators;

import java.util.Iterator;
import operators.comparison.Comparison;
import operators.structure.Row;


public class Restrict implements Iterable<Row>{
    private Iterable table;
    private String attribute;
    private Comparison comparisonOp;
    private Comparable value;
    private Row nextRow;
    
    public Restrict(Iterable source, Comparison comparisonOp, String attribute, Comparable value) {
        
        this.table = source;
        this.comparisonOp = comparisonOp;
        this.attribute = attribute;
        this.value = value;
        this.nextRow = null;
        
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
                if(nextRow == null) {  
                    
                    while (!comparisonOp.compare(originalRow.getValue(attribute), value)) {

                        originalRow = tableIterator.next();

                    }
                    
                    //Guardando as colunas da tabela
                    String[] columns = originalRow.getAttributes();

                    //Registro contendo as colunas da tabela
                    Row newRow = new Row(columns);

                    //Copiando todos os valores de todos os campos dessa linha
                    for (int i = 0; i < columns.length; i++) {
                        newRow.setValue(i, originalRow.getValue(columns[i]));
                    }
                    
                    originalRow = tableIterator.next();
                    while (!comparisonOp.compare(originalRow.getValue(attribute), value) && tableIterator.hasNext()) {

                        originalRow = tableIterator.next();

                    }
                    
                    //Guardando as colunas da tabela
                    columns = originalRow.getAttributes();

                    //Registro contendo as colunas da tabela
                    nextRow = new Row(columns);

                    //Copiando todos os valores de todos os campos dessa linha
                    for (int i = 0; i < columns.length; i++) {
                        nextRow.setValue(i, originalRow.getValue(columns[i]));
                    }
                    
                    return newRow;
                    
                } else {
                    Row newRow = nextRow;
                    
                    originalRow = tableIterator.next();
                    while (!comparisonOp.compare(originalRow.getValue(attribute), value) && tableIterator.hasNext()) {

                        originalRow = tableIterator.next();

                    }

                    //Guardando as colunas da tabela
                    String[] columns = originalRow.getAttributes();

                    //Registro contendo as colunas da tabela
                    nextRow = new Row(columns);

                    //Copiando todos os valores de todos os campos dessa linha
                    for (int i = 0; i < columns.length; i++) {
                        nextRow.setValue(i, originalRow.getValue(columns[i]));
                    }
                    return newRow;
                }
                
                /*Row newRow = null;
                while(tableIterator.hasNext()) {
                    
                    //Registro da tabela original
                    Row originalRow = tableIterator.next();

                    //Copia os valores da coluna original para a nova caso o valor da coluna especificada for igual ao valor na linha atual
                    if (comparisonOp.compare(originalRow.getValue(attribute), value)) {
                        
                        //Guardando as colunas da tabela
                        String[] columns = originalRow.getAttributes();
                        
                        //Registro contendo as colunas da tabela
                        newRow = new Row(columns);
                        
                        //Copiando todos os valores de todos os campos dessa linha
                        for (int i = 0; i < columns.length; i++) {
                            newRow.setValue(i, originalRow.getValue(columns[i]));
                        }
                        //return newRow;
                        
                    }
                    
                }*/
                
            }                    
        };
        
    }
}
