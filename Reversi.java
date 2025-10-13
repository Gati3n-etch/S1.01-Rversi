class Reversi{
	void principal(){
		String[][] t = tableau();
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
	String[][] tableau(){
		int taille=SimpleInput.getInt("Entrez la taille du tableau : ");
		while (taille%2!=0 || taille<4 || taille>16){
			System.out.println("La valeur n'est pas valide");
			taille=SimpleInput.getInt("Entrez la taille du tableau : ");
		}
		String[][] plateau =new String[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				plateau[i][j] = " ";
			}
		}
		int milieu = taille/2;
        plateau[milieu][milieu] = "O";
        plateau[milieu-1][milieu-1] = "O";
        plateau[milieu-1][milieu] = "X";
        plateau[milieu][milieu-1] = "X";
		return plateau;
	}
	
	
	int[] listeVoisin(int x; int y; String tab){
		int[] voisin = new int[];
		if(tab[y][x] !=
		
		
	
	void changementCase(int x; int y; String tab; String val){
		tab[y][x] = val;
	}
	
	
	void displayTab(String[][] t){
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
