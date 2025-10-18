class Reversi{
	void principal(){
		char[][] t = tableau();
		displayTab(t);
	}
	
	
	void lancementJeu(){
		int mode = choixMode();
		char[][] plateauJeu = tableau();
		displayTab(plateauJeu);
		int[] couleurPiontJoueur = choixJoueur(mode);
		int joueur1 = couleurPiontJoueur[0];
		int joueur2 = couleurPiontJoueur[1];
		/* Mode a 2 */
		if(mode==2){
			
		}
		/* Mode solo */
		else{
		
		}
	}


	int choixMode(){
		int modeJeu=SimpleInput.getInt("Choisissez le mode jeu entre solo (1) et duo (2) : ");
		while (modeJeu != 1 && modeJeu != 2){
			System.out.println("la valeur n'est pas valide");
			modeJeu=SimpleInput.getInt("Choisissez le mode jeu entre solo (1) et duo (2) : ");
		}
		return modeJeu;
	}
	
	
	int[] choixJoueur(int mode){
		int joueur1 = SimpleInput.getChar("Joueur 1 choisissez la couleur des pionts [ blanc (1) ou noir (2) ] : ");
		int joueur2 = 2;
		while(joueur1 != 1 && joueur1 != 2){
			System.out.println("Valeur invalide !");
			joueur1 = SimpleInput.getChar("Joueur 1 choisissez la couleur des pionts [ blanc (1) ou noir (2) ] : ");
		}
		if(mode==2){
			if(joueur1 == 1){
				System.out.println("Joueur 2 aura les pionts de couleurs noirs");
				joueur2 = 2;
			}
			else{
				System.out.println("Joueur 2 aura les pionts de couleurs blancs");
				joueur2 = 1;
			}
		}
		else{
			if(joueur1 == 1){
				System.out.println("Le robot aura les pionts de couleurs noirs");
				joueur2 = 2;
			}
			else{
				System.out.println("Le robot aura les pionts de couleurs blancs");
				joueur2 = 1;
			}
		}
		int[] piontJoueur = {joueur1,joueur2};
		return piontJoueur;
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
	
	
	
	void coupJouer(char[][] tab, int joueur){
		char signe = 'X';
		if(joueur == 1){
			signe = 'O';
		}
		int x = SimpleInput.getInt("Choisissez la colone : ");
		while(x<0 && x>=tab.length){
			System.out.println("Valeur saisi en dehors des limites du plateau");
			x = SimpleInput.getInt("Choisissez la colone : ");
		}
		int y = SimpleInput.getInt("Choisissez la ligne : ");
		while(y<0 && y>=tab.length){
			System.out.println("Valeur saisi en dehors des limites du plateau");
			y = SimpleInput.getInt("Choisissez la ligne : ");
		}
		while(estJouable(x,y,tab) == false){
			System.out.println("Ce coup ne peut pas etre jouer choisissez une autre case.");
			x = SimpleInput.getInt("Choisissez la colone : ");
			y = SimpleInput.getInt("Choisissez la ligne : ");
		}
		changementCase(x,y,tab,signe);
		/**
		 * Appel fonction pour remplacer les cases
		*/
		displayTab(tab);
	}
	
		
	
	boolean estJouable(int x, int y, char[][] tab){
		boolean jouable = false;
		if(y>=0 && y<tab.length && x>=0 && x<tab.length && tab[y][x] == ' '){
			int[][] voisins = listeVoisin(x, y, tab);
			for(int i = 0; i<voisins.length; i++){
				if(voisins[i][0] != -1){
					jouable = true;
				}
			}
		}else{
			jouable = false;
		}
		return jouable;
	}
	
	
	
	int[][] listeVoisin(int x, int y, char[][] tab){
		int[][] voisin = new int[4][2];
		if(x-1 >= 0 && tab[y][x-1] != ' '){
			voisin[0][0] = x;
			voisin[0][1] = y;
		}else{
			voisin[0][0] = -1;
			voisin[0][1] = -1;
		}
		if(x+1 < tab.length && tab[y][x+1] != ' '){
			voisin[1][0] = x;
			voisin[1][1] = y;
		}else{
			voisin[1][0] = -1;
			voisin[1][1] = -1;
		}
		if(y-1 >= 0 && tab[y-1][x] != ' '){
			voisin[1][0] = x;
			voisin[1][1] = y;
		}else{
			voisin[2][0] = -1;
			voisin[2][1] = -1;
		}
		if(y+1 < tab.length && tab[y+1][x] != ' '){
			voisin[3][0] = x;
			voisin[3][1] = y;
		}else{
			voisin[3][0] = -1;
			voisin[3][1] = -1;
		}
		return voisin;
	}
			
		
		
	void changementCase(int x, int y, char[][] tab, char val){
		tab[y][x] = val;
	}
	void verifDiagonaleD(char[][]t,int x, int y,int joueur){
		int i=1;
		int j=1;
		char signe='X';
		if ( joueur==1){
			signe='O';
		}
		while(t[x-i][y-i]!=' ' && t[x-i][y-i]!=signe){	
			i++;
		}
		if (i>2){
			while(t[x-j][y-j]!=' '&& t[x-j][y-j]!=signe){
				changementCase(x-j,y-j,t,signe);
				j++;
			}
		}else if(i<=2){
			i=1;
			j=1;
			while(t[x+i][y+i]!=' '&& t[x+i][y+i]!=signe){	
				i++;
			}
			if (i>2){
				while(t[x+j][y+j]!=' '&& t[x+j][y+j]!=signe){
					changementCase(x+j,y+j,t,signe);
					j++;
				}
			}
		}
	}
	void verifDiagonaleG(char[][]t,int x, int y,int joueur){
		int i=1;
		int j=1;
		char signe='X';
		if ( joueur==1){
			signe='O';
		}
		while(t[y-i][x-i]!=' ' && t[y-i][x-i]!=signe && i<t.length){	
			i++;
		}
		if (i>2){
			while(t[y-j][x-j]!=' '&& t[y-j][x-j]!=signe && i<t.length){
				changementCase(y-j,x-j,t,signe);
				j++;
			}
		}else if(i<=2){
			i=1;
			j=1;
			while(t[y+i][x+i]!=' '&& t[y+i][x+i]!=signe && j<t.length){	
				i++;
			}
			if (i>2){
				while(t[y+j][x+j]!=' '&& t[y+j][x+j]!=signe && j<t.length){
					changementCase(y+j,x+j,t,signe);
					j++;
				}
			}
		}
	}
	
	void verifLigne(char[][]t,int x, int y,int joueur){
		int i=1;
		int j=1;
		char signe='X';
		if ( joueur==1){
			signe='O';
		}
		while(t[x][y-i]!=' ' && t[x][y-i]!=signe){	
			i++;
		}
		if (i>2){
			while(t[x][y-j]!=' '&& t[x][y-j]!=signe){
				changementCase(x,y-j,t,signe);
				j++;
			}
		}else if(i<=2){
			i=1;
			j=1;
			while(t[x][y+i]!=' '&& t[x][y+i]!=signe){	
				i++;
			}
			if (i>2){
				while(t[x][y+j]!=' '&& t[x][y+j]!=signe){
					changementCase(x,y+j,t,signe);
					j++;
				}
			}
		}
	}
	void verifColonne(char[][]t,int x, int y,int joueur){
		int i=1;
		int j=1;
		char signe='X';
		if ( joueur==1){
			signe='O';
		}
		while(t[x-i][y]!=' ' && t[x-i][y]!=signe){	
			i++;
		}
		if (i>2){
			while(t[x-j][y]!=' '&& t[x-j][y]!=signe){
				changementCase(x-j,y,t,signe);
				j++;
			}
		}else if(i<=2){
			i=1;
			j=1;
			while(t[x+i][y]!=' '&& t[x+i][y]!=signe){	
				i++;
			}
			if (i>2){
				while(t[x+j][y]!=' '&& t[x+j][y]!=signe){
					changementCase(x+j,y,t,signe);
					j++;
				}
			}
		}
	}
	void regles(char[][]t,int x, int y,int joueur){
		if (verifColonne(t,x,y,joueur)!=displayTab(t)){
			verifColonne(t,x,y,joueur);
		}
		else if (verifLigne(t,x,y,joueur)!=displayTab(t)){
			verifLigne(t,x,y,joueur);
		}
		else if (verifDiagonaleD(t,x,y,joueur)!=displayTab(t)){
			verifDiagonaleD(t,x,y,joueur);
		}
		else if (verifDiagonaleG(t,x,y,joueur)!=displayTab(t)){
			verifDiagonaleG(t,x,y,joueur);
		}
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
				if(estJouable(x,y,t)){
					System.out.print("| ~ ");
				}else{
					System.out.print("| "+t[y][x]+" ");
				}
			}
			System.out.println("|");
		}
		System.out.print("  -");
		for(int a = 0; a < t.length ; a++){
			System.out.print("----");
		}
		System.out.println();
		System.out.println("O : piont blanc / X : piont noir / ~ : case valide");
		
	}			
}


