package org.example;

import org.example.Exception.BoardException;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if(rows < 1 || columns < 1 ){                                   //teste  se a linha e coluna informada é menor que 1

            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece [rows][columns];
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public Piece piece (int row , int columns) {                        //acessar a peca em uma determinada linha e coluna
        if (!positionExists(row, columns))   {                          //se a posicao nao existir, lancamos uma nova excecao

            throw new BoardException("Position not on the board");
        }
        return pieces [row][columns];
    }
    public Piece piece (Position position) {
        if (!positionExists(position))   {                              //se a posicao nao existir, lancamos uma nova excecao

            throw new BoardException("Position not on the board");
        }
        return pieces [position.getRow()][position.getColumn()];
    }
    public void placePiece (Piece piece, Position position){            //metodo vai na matriz de pecas na linhas position.get.. e coloca a peca na dada posicao

        if (thereIsAPiece(position)){                                   //teste para ver se já existe uma peca na posicao, antes de colocar uma nova

            throw new BoardException("There is already a piece on position " + position);
        }

        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;                                      //informar que peca nao tem mais valor null
    }

    public Piece removePiece(Position position){
        if (!positionExists(position)){
            throw new BoardException("Position not on the board");       //se a posicao nao existir, lancamos uma nova excecao
        }
        if (piece(position) == null){
            return null;
        }
        Piece aux = piece (position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] =null ;            //indica que nao tem mais peca para esta posicao,
        return aux;                                                        //variavel aux contem a peca que foi retirada
    }

    private boolean positionExists(int row , int column){               //verifica se a posicao EXISTE no tabuleiro
        return row>=0 && row < rows && column >=0 && column < columns;
    }
    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }
    public boolean thereIsAPiece(Position position) {                   //verifica se tem uma peca nesta posicao

        if (!positionExists(position))   {                              //(antes de lancar o método therIsAPiece no método placePiece, verifica antes se a posicao existe

            throw new BoardException("Position not on the board");
        }

        return piece(position) != null;
    }
}
