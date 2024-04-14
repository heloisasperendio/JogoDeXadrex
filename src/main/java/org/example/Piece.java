package org.example;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece( Board board) {
      //nao incluimos position pq inicialmente é nulo
        this.board = board;
        position = null; //podemos incluir assim também
    }

    //nao colocamos o set porque o tabuleiro nao pode ser alterado
    protected Board getBoard() { //uso exclusivo da camada do tabuleiro!
        return board;
    }
    public abstract boolean [][] possibleMoves();
    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];                       //método concreto utiliza um método abstrato
    }
    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();                                                     //matriz deve ser percorrida para verif. se existe alguma posicao verdadeira
        for(int i =0 ; i< mat.length; i ++){
            for(int j =0 ; j< mat.length; j ++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
