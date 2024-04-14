package org.example.chessPieces;

import org.example.Board;
import org.example.ChessPiece;
import org.example.Color;
import org.example.Position;

public class Horse extends ChessPiece {

    public Horse(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "H";
    }

    private boolean canMove(Position position){                                                   //fala se o pode mover para uma det. posicao
        ChessPiece p = (ChessPiece)getBoard().piece(position);                                    //pega a peca p que estiver nesta posicao
        return p == null || p.getColor() != getColor();                                           //nao pode ser nula e nem adversaria (cor diferente da peca p)
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][]mat = new boolean [getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);


        p.setVales(position.getRow()-1, position.getColumn()-2) ;
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setVales(position.getRow()-2 , position.getColumn() -1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setVales(position.getRow()-2, position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setVales(position.getRow()-1, position.getColumn()+ 2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setVales(position.getRow()+1, position.getColumn()+2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setVales(position.getRow()+2, position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setVales(position.getRow()+2, position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setVales(position.getRow()+1, position.getColumn()-2);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}


