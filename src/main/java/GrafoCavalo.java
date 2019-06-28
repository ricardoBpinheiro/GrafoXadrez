public class GrafoCavalo {

    private static final int N = 8; //numeros de linhas no tabuleiro

    private int tabuleiro[][];

    public GrafoCavalo() { //Construtor
        tabuleiro = new int[N][N];
    }

    private boolean movimentoSeguro(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N && tabuleiro[x][y] == -1){
            return true;
        }
        return false;
    }

    private void resolucao() {
        for (int x = 0; x < N; x++) {  //Faz a matriz de 8 por 8
            for (int y = 0; y < N; y++) {
                System.out.print("  " + tabuleiro[x][y]);
            }
            System.out.println();
        }
    }

    private boolean solveKTUtil(int x, int y, int movei, int moveEixoX[],int moveEixoY[]){
        //opcao de movimento valido  movei
        //
        int k, proxEixoX, proxEixoY;

        if (movei == N * N){
            return true;
        }

        for (k = 0; k < N; k++) {  //Tenta todos os movimentos das coordenadas x e y
            proxEixoX = x + moveEixoX[k];
            proxEixoY = y + moveEixoY[k];

            if (movimentoSeguro(proxEixoX, proxEixoY)) {
                tabuleiro[proxEixoX][proxEixoY] = movei;
                if (solveKTUtil(proxEixoX, proxEixoY, movei + 1, moveEixoX, moveEixoY)){
                    return true;
                }
                else{
                    tabuleiro[proxEixoX][proxEixoY] = -1;
                }
            }
        }
        return false;
    }

    public boolean movimentos() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                tabuleiro[x][y] = -1;
            }
        }

        int moveEixoX[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; //Movimentos possiveis no eixo X para o cavalo
        int moveEixoY[] = { 1, 2, 2, 1, -1, -2, -2, -1 }; //Movimentos possiveis no eixo Y para o cavalo

        tabuleiro[0][0] = 0; //valor aonde o cavalo se inicia

        if (!solveKTUtil(0, 0, 1, moveEixoX, moveEixoY)) {
            System.out.println("A solução não existe");
            return false;
        }
        else {
            resolucao();
        }
        return true;
    }

    public static void main(String[] args) {
        GrafoCavalo grafoCavalo = new GrafoCavalo();
        System.out.println("A solução para o problema do cavalo é: ");
        grafoCavalo.movimentos();
    }
}