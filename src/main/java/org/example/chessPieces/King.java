package org.example.chessPieces;

import org.example.Board;
import org.example.ChessPiece;
import org.example.Color;
import org.example.Position;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){                                                   //fala se o king pode mover para uma det. posicao
        ChessPiece p = (ChessPiece)getBoard().piece(position);                                    //pega a peca p que estiver nesta posicao
        return p == null || p.getColor() != getColor();                                           //nao pode ser nula e nem adversaria (cor diferente da peca p)
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][]mat = new boolean [getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //acinma

        p.setVales(position.getRow()-1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //aabaixo

        p.setVales(position.getRow()+1, position.getColumn());
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //esquerda

        p.setVales(position.getRow(), position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //direita

        p.setVales(position.getRow(), position.getColumn()+ 1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //noroeste

        p.setVales(position.getRow()-1, position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //nordeste

        p.setVales(position.getRow()-1, position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //sudoeste

        p.setVales(position.getRow()+1, position.getColumn()-1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //sudeste

        p.setVales(position.getRow()+1, position.getColumn()+1);
        if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }
}
