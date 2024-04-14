package org.example;

public abstract class ChessPiece extends Piece {

    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {                 //Board repassa a chamada para o contrutor da superclasse (construtor da classe Piece herdada)
        super(board);
        this.color = color;
    }
    public Color getColor() {                                     //nao podemos modificar a cor
        return color;
    }
    public int getmoveCount() {                                     //nao podemos modificar a cor
        return moveCount;
    }
    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }

    public ChessPosition getChessPosition(){                      //pega a position e converte para chessPosition
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;                 //testa se a peca é adversaria
    }
}
