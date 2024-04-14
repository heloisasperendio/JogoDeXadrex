package org.example;

import org.example.Exception.ChessException;
import org.example.chessPieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {                                                                //regras do jogo!!

    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();


    public ChessMatch() {                                                                //Dizendo que o tabuleiro é 8 x 8
       board = new Board(8,8);
       turn =1;
       currentPlayer = Color.WHITE;
       check = false;                                                                    //colocado para enfatizar que propriendade boolean sempre inicia como falso
       initialSetup();                                                                   //chama o método initialSetup
    }

    public int getTurn(){
        return turn;
    }
    public Color getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean getCheck (){
        return check;
    }

    public boolean getCheckMate (){
        return checkMate;
    }
    public ChessPiece [][] getPieces(){                                                  //Retorna uma matriz de pecas de xadrez correspondentes a partida
                                                                                         //qtd de linhas e colunas do tabuleiro
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];        //programa enxerga a peca de xadrez, e nao a peca interna do tabuleiro

        for  (int i = 0 ; i < board.getRows(); i++){                                     //percorrer linhas da matriz
            for  (int j = 0 ; j < board.getColumns(); j++){                              //percorrer colunas da matriz
                mat [i][j] = (ChessPiece) board.piece(i,j);                              //para cada posicao i j do tabuleiro, a matriz mat receberá a peca
            }
        }
        return mat;
    }
    public boolean [][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();                                  //converter posicao origem e detino para posicao da matriz
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source,target);                                           //verifica se posicoa source é valida
        Piece capturedPiece = makeMove(source,target);                                   //realiza movimento da peca

        if(testCheck(currentPlayer)){                                                    //depois de executar o mov. testa se isso colocou o proprio jogador em cheque
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;                    //testa se o oponente ficou em cheque, se sim, atualiza a propriedade cheque como verdadeira, se nao, falsa

        if (testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }
        else {

            nextTurn();
        }
        return(ChessPiece) capturedPiece;
    }
    private Piece makeMove(Position source, Position target){                            //removemos a possivel peca na posicao de detino (sera a peca capturada capturedPiece)
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){         //metodo para desfazer a jogada
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece (p, source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position position){                              //se nao existir peca nesta posicao da excecao
        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {            //se a cor for diferente da posicao, a peca é do adversario!
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(position).isThereAnyPossibleMove())    {                         //verifica se existe movimento possiveis para a peca
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }
    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void nextTurn(){
        turn++;                                                                          //incrementear turno e mudar jogador atual, para outra cor
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent (Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;                      //se a cor que passei como argumento é White, RETORNA black, caso contrario retorna white
    }

    private ChessPiece king(Color color){                                               //metodo para filtrar a lista e procurar o rei da cor informada

        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            if (p instanceof King) {
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board" );
    }

    private boolean testCheck (Color color) {                                            //verifica se o rei desta cor está em cheque

        Position kingPosition = king(color).getChessPosition().toPosition();             //pega a posicao do rei no formato de materiz chamando o metodo king passando a cor por argumento, e

        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());

        for (Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();                                        //matriz de mov. possiveis da peca adversaria p
            if ( mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
                }
            }
            return false;
    }

    private boolean testCheckMate (Color color) {
        if(!testCheck(color)){
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list){
            boolean [][] mat = p.possibleMoves();
            for (int i = 0; i< board.getRows();i++){
                for (int j = 0; j< board.getRows();j++){
                    if(mat [i][j]){
                       Position source = ((ChessPiece)p).getChessPosition().toPosition();
                       Position target = new Position(i,j);
                       Piece capturedPiece = makeMove (source, target);
                       boolean testCheck = testCheck (color);                            //testa se o rei da minha cor ainda esta em cheque
                        undoMove(source, target, capturedPiece);
                        if(!testCheck){
                            return false;                                                //se existir alguma peca p na lista que tenha o movimento que tira do cheque, retorna falso (nao esta em cheque mate)
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {                 //metodo recebe as coordenadas (operacao de colocar a peca a partir das coordenadas)
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);                                                     //já coloco na lista de pecas alem de colocar no tabuleiro
    }


    private void initialSetup(){                                                         //responsavel por iniciar a partida, colocando as pecas no tabuleiro informado as coordenadas
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Horse(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Horse(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Horse(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Horse(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));

    }
}
