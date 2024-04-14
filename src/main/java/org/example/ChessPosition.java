package org.example;

import org.example.Exception.ChessException;

public class ChessPosition {                                                                                      //recebe as coordenadas e converte para a posicao
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row <1 || row > 8){                                                   //testa se as cordenadas informadas estao corretas
            throw new ChessException("Error instanciating ChessPosition. Valid values are from a1 to h8.");
        }
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    public Position toPosition(){                                                                              //converte a posicao em cordenadas para posicao na matriz
        return new Position(8- row, column - 'a');                                                     // formula para achar a coluna da matriz
    }

    protected static ChessPosition fromPosition(Position position){                                                //converte a posicao da matriz para posicao em coordenadas
        return new ChessPosition((char) ('a'+ position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

}
