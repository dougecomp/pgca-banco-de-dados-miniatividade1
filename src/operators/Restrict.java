package operators;

import java.util.Iterator;
import operators.comparison.Comparison;
import operators.structure.Row;


public class Restrict implements Iterable<Row>{
    private Iterable table;
    private String attribute;
    private Comparison comparisonOp;
    private Comparable value;
    private Row nextRow; // Atributo para armazenar a próxima linha que deve ser retornada
    
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
                if(nextRow == null) { // se nextRow ainda é nulo então deve-se buscar duas linhas. Retornando a primeira e guardando a próxima nessa variável
                    
                    while (!comparisonOp.compare(originalRow.getValue(attribute), value)) { // Quando sair desse loop, achou a condição do restrict

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
                    // Aqui newRow contém a linha que deve ser retornada
                    
                    // Aqui começa o processo para buscar a segunda linha.
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
                    } // Armazena o resultado da próxima linha
                    
                    return newRow; // Retorna a primeira linha já encontrada antes
                    
                } else { // Se nextRow não for nulo, então buscar somente uma próxima linha
                    
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
                    } // Guarda a próxima linha
                    
                    // Retorna a que estava guardada antes
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
