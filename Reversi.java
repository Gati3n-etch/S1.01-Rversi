class Reversi{
	void principal(){
		char[][] t = tableau();
		displayTab(t);
	}
	

	int choix_mode(){
		int modeJeu=SimpleInput.getInt("Choisissez le mode jeu entre solo (1) et duo (2) : ");
		while (modeJeu != 1 && modeJeu != 2){
			System.out.println("la valeur n'est pas valide");
			modeJeu=SimpleInput.getInt("Choisissez le mode jeu entre solo (1) et duo (2) : ");
		}
		return modeJeu;
	}
	char[][] tableau(){
		int taille=SimpleInput.getInt("Entrez la taille du tableau : ");
		while (taille%2!=0 || taille<4 || taille>16){
			System.out.println("La valeur n'est pas valide");
			taille=SimpleInput.getInt("Entrez la taille du tableau : ");
		}
		char[][] plateau =new char[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				plateau[i][j] = ' ';
			}
		}
		int milieu = taille/2;
        plateau[milieu][milieu] = 'O';
        plateau[milieu-1][milieu-1] = 'O';
        plateau[milieu-1][milieu] = 'X';
        plateau[milieu][milieu-1] = 'X';
		return plateau;
	}
	
	
	void estJouable(int x, int y, char[][] tab){
		boolean jouable = true;
		if(tab[y][x] == ' '){
			int[][] voisins = listeVoisin(x, y, tab);
			for(int i = 0; i<voisins.length; i++){
				if(voisins[i][0] == -1){
					jouable = false;
				}
			}
		}else{
			jouable = false;
		}
		return jouable;
	}
	
	
	int[][] listeVoisin(int x, int y, char[][] tab){
		int[][] voisin = new int[4][2];
		if(tab[y][x-1] != ' ' && x-1 >= 0){
			voisin[0][0] = x;
			voisin[0][1] = y;
		}
		else if(tab[y][x-1] == ' ' && x-1 >= 0){
			voisin[0][0] = -1;
			voisin[0][1] = -1;
		}
		else if(tab[y][x+1] != ' ' && x+1 < tab.length){
			voisin[1][0] = x;
			voisin[1][1] = y;
		}
		else if(tab[y][x+1] == ' ' && x+1 < tab.length ){
			voisin[1][0] = -1;
			voisin[1][1] = -1;
		}
		else if(tab[y-1][x] != ' ' && y-1 >= 0){
			voisin[1][0] = x;
			voisin[1][1] = y;
		}
		else if(tab[y-1][x] == ' ' && y-1 >= 0){
			voisin[2][0] = -1;
			voisin[2][1] = -1;
		}
		else if(tab[y+1][x] != ' ' && y+1 < tab.length){
			voisin[3][0] = x;
			voisin[3][1] = y;
		}
		else if(tab[y+1][x] == ' ' && y+1 < tab.length ){
			voisin[3][0] = -1;
			voisin[3][1] = -1;
		}
		
		return voisin;
	}
			
		
		
	
	void changementCase(int x, int y, char[][] tab, char val){
		tab[y][x] = val;
	}
	
	
	void displayTab(char[][] t){
		String ligne_haut = "    ";
		for(int i = 0; i < t.length ; i++){
			if(i>=10){
				ligne_haut = ligne_haut + i + "  ";
			}else{
				ligne_haut = ligne_haut + i + "   ";
			}
		}
		System.out.print(ligne_haut);
		System.out.println();
		for(int y = 0; y < t.length ; y++){
			System.out.print("  -");
			for(int a = 0; a < t.length ; a++){
				System.out.print("----");
			}
			System.out.println();
			if(y>=10){
				System.out.print(y);
			}else{
				System.out.print(y+" ");
			}
			for(int x = 0; x < t.length ; x++){
				System.out.print("| "+t[y][x]+" ");
			}
			System.out.println("|");
		}
		System.out.print("  -");
		for(int a = 0; a < t.length ; a++){
			System.out.print("----");
		}
		
	}			
}
