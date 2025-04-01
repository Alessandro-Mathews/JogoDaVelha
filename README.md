Código utilizando o Framework Processing:
int[][] tabuleiro = new int[3][3]; 
int jogador = 1;  
boolean jogoAtivo = true;
int tamanhoCelula = 95;

void setup() {
  size(300, 400);  
  inicializarTabuleiro();
}

void draw() {
  background(255);
  desenharTabuleiro();
  if (jogoAtivo) {
    verificarPartida();
  }
}

void mousePressed() {
  if (jogoAtivo) {
    int linha = mouseY / tamanhoCelula;
    int coluna = mouseX / tamanhoCelula;
    
    if (linha < 3 && coluna < 3 && tabuleiro[linha][coluna] == 0) {
      tabuleiro[linha][coluna] = jogador;
      jogador = (jogador == 1) ? 2 : 1;  
    }
  }
}

void desenharTabuleiro() {
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      stroke(0);
      noFill();
      rect(j * tamanhoCelula, i * tamanhoCelula, tamanhoCelula, tamanhoCelula);
      
      if (tabuleiro[i][j] == 1) {
        linhaX(j, i);
      } else if (tabuleiro[i][j] == 2) {
        circuloO(j, i);
      }
    }
  }
}

void linhaX(int col, int row) {
  float x1 = col * tamanhoCelula;
  float y1 = row * tamanhoCelula;
  line(x1, y1, x1 + tamanhoCelula, y1 + tamanhoCelula);
  line(x1 + tamanhoCelula, y1, x1, y1 + tamanhoCelula);
}

void circuloO(int col, int row) {
  float x = col * tamanhoCelula + tamanhoCelula / 2;
  float y = row * tamanhoCelula + tamanhoCelula / 2;
  ellipse(x, y, tamanhoCelula * 0.8, tamanhoCelula * 0.8);
}

void inicializarTabuleiro() {
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      tabuleiro[i][j] = 0; // 0 indica que a célula está vazia
    }
  }
}

void verificarPartida() {
  // Verificar linhas, colunas 
  for (int i = 0; i < 3; i++) {
    if ((tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][0] == tabuleiro[i][2] && tabuleiro[i][0] != 0) || 
        (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[0][i] == tabuleiro[2][i] && tabuleiro[0][i] != 0)) {
      anunciarVencedor(tabuleiro[i][i]);
      return;
    }
  }
  
  // Verificar diagonais
  if ((tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2] && tabuleiro[0][0] != 0) || 
      (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0] && tabuleiro[0][2] != 0)) {
    anunciarVencedor(tabuleiro[1][1]);
    return;
  }

  // Verificar empate
  boolean empate = true;
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      if (tabuleiro[i][j] == 0) {
        empate = false;
        break;
      }
    }
  }
  if (empate) {
    anunciarEmpate();
  }
}

void anunciarVencedor(int vencedor) {
  jogoAtivo = false;
  if (vencedor == 1) {
    println("Jogador X venceu!");
  } else {
    println("Jogador O venceu!");
  }
}

void anunciarEmpate() {
  jogoAtivo = false;
  println("Empate!");
}

