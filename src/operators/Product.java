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
        
        // Copiando os atributos das duas tabelas que estão sendo feitas o produto
        // Necessário para que quando eu instancia a nova row com todos os valores, eu já passe para o construtor do Row as colunas
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
        private Row nextRow; // Armazenar a próxima linha a ser retornada quando .next for chamado
        
        // Row Pivô que guarda a linha atual da primeira tabela
        // Ela serve para que eu consiga fazer com que o algoritmo percorra todas as linhas da segunda tabela sem mudar o da primeira
        // Só será alterada caso a segunda tabela for percorrida toda. Caso aconteça, então vai para a próxima linha da primeira tabela
        private Row pivo;
       
        public MyIterator() {
            // Inicializa iteradores, pivo e já busca próxima linha
            tableIterator1 = tabela1.iterator();
            tableIterator2 = tabela2.iterator();
            pivo = tableIterator1.next();
            nextRow = getNextRow();
        }
        
        @Override
        public boolean hasNext() {
            return nextRow != null; // Enquanto houver próxima linha a ser retornada então tem próximo
        }

        @Override
        public Row next() {
           Row temp = nextRow; // Salva no temp para retornar
           nextRow = getNextRow(); // Recupera o próximo Row que deverá ser retornado na próxima chamada
           return temp;
        }
        
        private Row getNextRow() {
            Row rowTable2 = null;
            Row newRow = new Row(todosAtributos);
            
            while(pivo != null) { // Enquanto não passar por todos os "pivôs" da tabela1
                
                for(int i=0;i<pivo.getAttributes().length;i++) {
                    // Preenche o novo Row com os valores do pivô
                    newRow.setValue(i, pivo.getValue(pivo.getAttributes()[i]));
                }
                while(tableIterator2.hasNext()) {
                    rowTable2 = tableIterator2.next();
                    for(int i=0;i<rowTable2.getAttributes().length;i++) {
                        // Preenche o novo Row com os valores da linha da segunda tabela.
                        // Nesse ponto já foram inseridos as informações do pivô
                        newRow.setValue(i+pivo.getAttributes().length, rowTable2.getValue(rowTable2.getAttributes()[i]));
                    }
                    // Enquanto houver linha na segunda tabela então o algoritmo irá retornar o novo Row
                    return newRow;
                    
                }
                
                // Caso saia do while tem linha na segunda tabela
                // Reinicia o iterator da tabela 2
                tableIterator2 = tabela2.iterator();
                
                // Verificar se a tabela existe próxima linha e caso tenha troca o pivô
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
