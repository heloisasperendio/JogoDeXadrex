package org.example.chessPieces;

import org.example.Board;
import org.example.ChessPiece;
import org.example.Color;
import org.example.Position;

public class Pawn extends ChessPiece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        if(getColor() == Color.WHITE){
            p.setVales(position.getRow() - 1, position.getColumn());                                         //se a posicao de uma linha acima dele (-1) existir e estar vazia, pode mov er para lá no primeiro movimento dele pode mover 2 casas para frente se nao existir peca (-2)
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setVales(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getmoveCount() == 0){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setVales(position.getRow() - 1, position.getColumn()-1);                                         //se a posicao de uma linha acima dele (-1) existir e estar vazia, pode mov er para lá no primeiro movimento dele pode mover 2 casas para frente se nao existir peca (-2)
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setVales(position.getRow() - 1, position.getColumn()+1);                                         //se a posicao de uma linha acima dele (-1) existir e estar vazia, pode mov er para lá no primeiro movimento dele pode mover 2 casas para frente se nao existir peca (-2)
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        else{
            p.setVales(position.getRow() + 1, position.getColumn());                                                    // como peao preto anda para baixo, é + entao trocamos
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setVales(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getmoveCount() == 0){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setVales(position.getRow() + 1, position.getColumn()-1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setVales(position.getRow() + 1, position.getColumn()+1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
