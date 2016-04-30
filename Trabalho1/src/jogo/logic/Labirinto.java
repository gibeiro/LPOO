package jogo.logic;

import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * The Class Labirinto.
 */
public class Labirinto {
	
	/**
	 * Generates a maze.
	 *
	 * @param size the maze's size
	 * @param ndragoes the number of dragons to add
	 * @return the char[][] maze
	 */
    public static char[][] geraLabirinto(int size,int ndragoes){
    	char[][] tabuleiro = new char[size][size];
    	if(size % 2 == 0)
			return null;
    	
		int pointerX = 2;//apontador que vai gerar caminho
		int pointerY = 2;
        tabuleiro = new char[size][size];
        char[][] visited = new char[(size-1)/2][(size-1)/2];
        int nvisited = 0;
		Stack<Integer> visitadosX = new Stack<Integer>();
		Stack<Integer> visitadosY= new Stack<Integer>();;
		// coloca tudo com X no tabuleiro
		for(int i = 0; i < size;i++){
			for(int j = 0; j < size;j++)
				tabuleiro[i][j] = 'X';
		}
		for(int i = 0; i < (size-1)/2;i++){
			for(int j = 0; j < (size-1)/2;j++)
				visited[i][j] = 'X';
		}
		// coloca os espaços vazios
		for(int i = 1; i < size-1 ; i+= 2){
			for(int j = 1 ; j < size-1;j+= 2)
				tabuleiro[i][j] = ' ';
		}
		// gerar saida
		
		int randomLengthExit = (int)(Math.random() * size);
		while(randomLengthExit % 2 == 0){
			randomLengthExit = (int)(Math.random() * size);
		}
		int randomSideExit = (int)(Math.random() * 4);
		if(randomSideExit == 0){
			tabuleiro[0][randomLengthExit] = 'S';
			pointerX = randomLengthExit;
			pointerY = 1;
		}
		if(randomSideExit == 1){
			tabuleiro[size-1][randomLengthExit] = 'S';
			pointerX = randomLengthExit;
			pointerY = size-2;
		}
		if(randomSideExit == 2){
			tabuleiro[randomLengthExit][0] = 'S';
			pointerY = randomLengthExit;
			pointerX = 1;
		}
		if(randomSideExit == 3){
			tabuleiro[randomLengthExit][size-1] = 'S';
			pointerY = randomLengthExit;
			pointerX = size-2;
		}
		int randomDirection = -1;
		boolean visited0 = false;
		boolean visited1 = false;
		boolean visited2 = false;
		boolean visited3 = false;
		
		while(true){
			nvisited++;
			visitadosX.push(new Integer(pointerX));
			visitadosY.push(new Integer(pointerY));
			visited[(pointerY-1)/2][(pointerX-1)/2] = ' ';
			visited0 = false;
			visited1 = false;
			visited2 = false;
			visited3 = false;
			if(nvisited == ((size-1)/2)*((size-1)/2))
				break;
			
			while(true){
				randomDirection = (int)(Math.random()*4);// 0 cima 1 baixo 2 esq 3 dir
				if(visited0 && visited1 && visited2 && visited3){
					pointerX = visitadosX.peek();
					pointerY = visitadosY.peek();
					visitadosX.pop();
					visitadosY.pop();
					visited0 = false;
					visited1 = false;
					visited2 = false;
					visited3 = false;
					continue;
				}
				if(randomDirection == 0){
					visited0 = true;
					if(pointerY-2 < 1)
						continue;
					if(visited[(pointerY-3)/2][(pointerX-1)/2] == ' ')
						continue;
					tabuleiro[pointerY-1][pointerX] = ' ';
					visited[(pointerY-3)/2][(pointerX-1)/2] = ' ';
					pointerY -= 2;		
					break;
				}
				if(randomDirection == 1){
					visited1 = true;
					if(pointerY+2 > size-1)
						continue;
					if(visited[(pointerY+1)/2][(pointerX-1)/2] == ' ')
						continue;
					tabuleiro[pointerY+1][pointerX] = ' ';
					visited[(pointerY+1)/2][(pointerX-1)/2] = ' ';
					pointerY += 2;
					
					break;
				}
				if(randomDirection == 2){
					visited2 = true;
					if(pointerX-2 < 1)
						continue;
					if(visited[(pointerY-1)/2][(pointerX-3)/2] == ' ')
						continue;
					tabuleiro[pointerY][pointerX-1] = ' ';
					visited[(pointerY-1)/2][(pointerX-3)/2] = ' ';
					pointerX -= 2;
					
					break;
				}
				if(randomDirection == 3){
					visited3 = true;
					if(pointerX+2 > size-1)
						continue;
					if(visited[(pointerY-1)/2][(pointerX+1)/2] == ' ')
						continue;
					tabuleiro[pointerY][pointerX+1] = ' ';
					visited[(pointerY-1)/2][(pointerX+1)/2] = ' ';
					pointerX += 2;
					
					break;
				}
				
			}
		}
		int heroiRandX = (int)(Math.random()*size);
		int heroiRandY = (int)(Math.random()*size);
		while(tabuleiro[heroiRandY][heroiRandX] != ' '){
			heroiRandX = (int)(Math.random()*size);
			heroiRandY = (int)(Math.random()*size);
		}
		tabuleiro[heroiRandY][heroiRandX] = 'H';
		int dragaoRandX = (int)(Math.random()*size);
		int dragaoRandY = (int)(Math.random()*size);
		int counter = ndragoes;
		while(counter != 0){
		while(tabuleiro[dragaoRandY][dragaoRandX] != ' ' || (Math.abs(dragaoRandY-heroiRandY) < 2 || Math.abs(dragaoRandX-heroiRandX) < 2)){
			dragaoRandX = (int)(Math.random()*size);
			dragaoRandY = (int)(Math.random()*size);
		}
		tabuleiro[dragaoRandY][dragaoRandX] = 'D';
		counter--;
		}
		int espadaRandX = (int)(Math.random()*size);
		int espadaRandY = (int)(Math.random()*size);
		while(tabuleiro[espadaRandY][espadaRandX] != ' '){
			espadaRandX = (int)(Math.random()*size);
			espadaRandY = (int)(Math.random()*size);
		}
		tabuleiro[espadaRandY][espadaRandX] = 'E';
		return tabuleiro;
    }
}
