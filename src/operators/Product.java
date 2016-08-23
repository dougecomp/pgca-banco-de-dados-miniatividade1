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
        
        // Copiando os atributos das duas tabelas que est�o sendo feitas o produto
        // Necess�rio para que quando eu instancia a nova row com todos os valores, eu j� passe para o construtor do Row as colunas
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
        private Row nextRow; // Armazenar a pr�xima linha a ser retornada quando .next for chamado
        
        // Row Piv� que guarda a linha atual da primeira tabela
        // Ela serve para que eu consiga fazer com que o algoritmo percorra todas as linhas da segunda tabela sem mudar o da primeira
        // S� ser� alterada caso a segunda tabela for percorrida toda. Caso aconte�a, ent�o vai para a pr�xima linha da primeira tabela
        private Row pivo;
       
        public MyIterator() {
            // Inicializa iteradores, pivo e j� busca pr�xima linha
            tableIterator1 = tabela1.iterator();
            tableIterator2 = tabela2.iterator();
            pivo = tableIterator1.next();
            nextRow = getNextRow();
        }
        
        @Override
        public boolean hasNext() {
            return nextRow != null; // Enquanto houver pr�xima linha a ser retornada ent�o tem pr�ximo
        }

        @Override
        public Row next() {
           Row temp = nextRow; // Salva no temp para retornar
           nextRow = getNextRow(); // Recupera o pr�ximo Row que dever� ser retornado na pr�xima chamada
           return temp;
        }
        
        private Row getNextRow() {
            Row rowTable2 = null;
            Row newRow = new Row(todosAtributos);
            
            while(pivo != null) { // Enquanto n�o passar por todos os "piv�s" da tabela1
                
                for(int i=0;i<pivo.getAttributes().length;i++) {
                    // Preenche o novo Row com os valores do piv�
                    newRow.setValue(i, pivo.getValue(pivo.getAttributes()[i]));
                }
                while(tableIterator2.hasNext()) {
                    rowTable2 = tableIterator2.next();
                    for(int i=0;i<rowTable2.getAttributes().length;i++) {
                        // Preenche o novo Row com os valores da linha da segunda tabela.
                        // Nesse ponto j� foram inseridos as informa��es do piv�
                        newRow.setValue(i+pivo.getAttributes().length, rowTable2.getValue(rowTable2.getAttributes()[i]));
                    }
                    // Enquanto houver linha na segunda tabela ent�o o algoritmo ir� retornar o novo Row
                    return newRow;
                    
                }
                
                // Caso saia do while tem linha na segunda tabela
                // Reinicia o iterator da tabela 2
                tableIterator2 = tabela2.iterator();
                
                // Verificar se a tabela existe pr�xima linha e caso tenha troca o piv�
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
