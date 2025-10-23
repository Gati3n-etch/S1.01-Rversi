class Reversi{
	void principal(){
		testScore();
		lancementJeu();
	}
	
	
	void lancementJeu(){
		int mode = choixMode();
		char[][] plateauJeu = tableau();
		displayTab(plateauJeu);
		int[] couleurPiontJoueur = choixJoueur(mode);
		int joueur1 = couleurPiontJoueur[0];
		int joueur2 = couleurPiontJoueur[1];
		int tour = 0;
		boolean fini = false;
		/* Mode a 2 */
		if(mode==2){
			while(fini == false){
				if(tour%2 == joueur1%2){
					System.out.println("Au tour du joueur 1 de jouer");
					coupJouer(plateauJeu,joueur1);
				}else{
					System.out.println("Au tour du joueur 2 de jouer");
					coupJouer(plateauJeu,joueur2);
				}
				tour++;
				fini = estTerminer(plateauJeu);
			}
			int gagnant = resultat(plateauJeu);
			if(gagnant == joueur1){
				System.out.println("Le joueur 1 à gagné !");
			}else if(gagnant == joueur2){
				System.out.println("Le joueur 2 à gagné !");
			}else{
				System.out.println("Egalité !");
			}
		}
		/* Mode solo */
		else{
			while(fini == false){
				if(tour%2 == joueur1){
					coupJouer(plateauJeu,joueur1);
				}else{
					coupJouer(plateauJeu,joueur2);
				}
				fini = estTerminer(plateauJeu);
			}
			int gagnant = resultat(plateauJeu);
			if(gagnant == joueur1){
				System.out.println("Le joueur 1 à gagné !");
			}else if(gagnant == joueur2){
				System.out.println("Le robot à gagné !");
			}else{
				System.out.println("Egalité !");
			}
		}
	}
	
	
	boolean estTerminer(char[][] tab){
		boolean terminer = false;
		if(estPresent(tab,'O') == false){
			terminer = true;
		}else if(estPresent(tab,'X') == false){
			terminer = true;
		}else{
			boolean plateauPlein = true;
			for(int i = 0; i < tab.length; i++){
				for(int j = 0; j < tab.length; j++){
					if(estJouable(j,i,tab)){
						plateauPlein = false;
					}
				}
			}
			if(plateauPlein == true){
				terminer = true;
			}
		}
		return terminer;
	}
	
	
	
	boolean estPresent(char[][] tab, char signe){
		boolean present = false;
		for(int i = 0; i < tab.length; i++){
			for(int j = 0; j < tab.length; j++){
				if(tab[i][j] == signe){
					present = true;
				}
			}
		}
		return present;
	}
				
				
	int resultat(char[][]tab){
		int compteurO = score(tab,'O');
		int compteurX = score(tab,'X');
		int gagnant;
		if(compteurO > compteurX){
			gagnant = 1;
		}else if(compteurO < compteurX){
			gagnant = 2;
		}else{
			gagnant = 0;
		}
		return gagnant;
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
		int joueur1 = SimpleInput.getInt("Joueur 1 choisissez la couleur des pionts [ blanc (1) ou noir (2) ] : ");
		int joueur2 = 2;
		while(joueur1 != 1 && joueur1 != 2){
			System.out.println("Valeur invalide !");
			joueur1 = SimpleInput.getInt("Joueur 1 choisissez la couleur des pionts [ blanc (1) ou noir (2) ] : ");
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
		verifLigne(tab,x,y,joueur);
		verifColonne(tab,x,y,joueur);
		verifDiagonaleHD_BG(tab,x,y,joueur);
		verifDiagonaleHG_BD(tab,x,y,joueur);
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
	
	
	int[][] listeCoupJouable(char[][]t){
        int cpt=0;
        int cptTab=0;
        for(int i=0;i<t.length;i++){
            for(int j=0;j<t.length;j++){
                if(estJouable(j,i,t)){
                    cpt++;
                }
            }
        }
        int[][] tab=new int[cpt][2];
        for(int i=0;i<t.length;i++){
            for(int j=0;j<t.length;j++){
                if(estJouable(j,i,t)){
                    tab[cptTab][0]=j;
                    tab[cptTab][1]=i;
                    cptTab++;
                }
            }
        }
        return tab;
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
	
	
	void verifDiagonaleHD_BG(char[][]t,int x, int y,int joueur){
        int i=x+1;
        int k=y-1;
        char signe='X';
        char autre='O';
        int compteur = 0;
        boolean borner=false;
        if ( joueur==1){
            signe='O';
            autre='X';
        }
        while(k>=0 && i<t.length && t[k][i]==autre){
            if(k-1>=0 && i+1<t.length && t[k-1][i+1]==signe){
                borner=true;
            }
            compteur++;
            i++;
            k--;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y-j][x+j] == autre){
					changementCase(x+j,y-j,t,signe);
				}
            }
        }
        compteur = 0;
        borner=false;
        i=x-1;
        k=y+1;
        while(k<t.length && i>=0 && t[k][i]==autre){
            if(k+1<t.length && i-1>=0 && t[k+1][i-1]==signe){
                borner=true;
            }
            compteur++;
            i--;
            k++;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y+j][x-j] == autre){
					changementCase(x-j,y+j,t,signe);
				}
            }
        }
    }
    
    
    void verifDiagonaleHG_BD(char[][]t,int x, int y,int joueur){
        int i=x-1;
        int k=y-1;
        char signe='X';
        char autre='O';
        int compteur = 0;
        boolean borner=false;
        if ( joueur==1){
            signe='O';
            autre='X';
        }
        while(i>=0 && k>=0 && t[k][i]==autre){
            if(i-1>=0 && k-1>=0 && t[k-1][i-1]==signe){
                borner=true;
            }
            compteur++;
            i--;
            k--;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y-j][x-j] == autre){
					changementCase(x-j,y-j,t,signe);
				}
            }
        }
        compteur = 0;
        borner=false;
        i=x+1;
        k=y+1;
        while(i<t.length && k<t.length && t[k][i]==autre){
            if(i+1<t.length && k+1<t.length && t[k+1][i+1]==signe){
                borner=true;
            }
            compteur++;
            i++;
            k++;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y+j][x+j] == autre){
					changementCase(x+j,y+j,t,signe);
				}
            }
        }
    }
	

	void verifLigne(char[][]t,int x, int y,int joueur){
        int i=x-1;
        char signe='X';
        char autre='O';
        boolean borner=false;
        int compteur = 0;
        if ( joueur==1){
            signe='O';
            autre='X';
        }
        while(i>=0 && t[y][i]==autre){
            if(i-1>=0 && t[y][i-1]==signe){
				borner=true;
			}
			compteur++;
			i--;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y][x-j] == autre){
					changementCase(x-j,y,t,signe);
				}
            }
        }
        compteur = 0;
        borner=false;
        i=x+1;
        while(i<t.length && t[y][i]==autre){
            if(i+1<t.length && t[y][i+1]==signe){
				borner=true;
			}
			compteur++;
			i++;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y][x+j] == autre){
					changementCase(x+j,y,t,signe);
				}
            }
        }
    }


    void verifColonne(char[][]t,int x, int y,int joueur){
        int i=y-1;
        char signe='X';
        char autre='O';
        boolean borner=false;
        int compteur = 0;
        if ( joueur==1){
            signe='O';
            autre='X';
        }
        while(i>=0 && t[i][x]==autre){
            if(i-1>=0 && t[i-1][x]==signe){
                borner=true;
            }
            compteur++;
            i--;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y-j][x] == autre){
					changementCase(x,y-j,t,signe);
				}
            }
        }
        borner=false;
        compteur = 0;
        i=y+1;
        while(i<t.length && t[i][x]==autre){
            if(i+1<t.length && t[i+1][x]==signe){
                borner=true;
            }
            compteur++;
            i++;
        }
        if (borner){
            for(int j=0;j<=compteur;j++){
				if(t[y+j][x] == autre){
					changementCase(x,y+j,t,signe);
				}
            }
        }
    }

	
	int score(char[][] tab, char signe){
		int compteur = 0;
		for(int y = 0; y < tab.length; y++){
			for(int x = 0; x < tab.length; x++){
				if(tab[y][x] == signe){
					compteur++;
				}
			}
		}
		return compteur;
	}
	
    /**
	Teste la méthode regles()
	*/
	void testScore () {
		char[][] t1={{' ','X',' ',' '},{'O','O','O',' '},{' ','O','O',' '},{' ',' ','X',' '}};
		char[][] t2={{' ',' ','O',' '},{' ','O','X',' '},{' ','X','O',' '},{' ',' ',' ',' '}};
		char[][] t3={{'O',' ',' ',' '},{' ','O','X',' '},{' ','X','O',' '},{' ',' ',' ','O'}};
		char[][] t4={{' ',' ',' ','X'},{' ','O','X',' '},{' ','X','O',' '},{'X',' ',' ',' '}};

        System.out.println ();
        System.out.println ("**testScore()**");
        testCasScore(t1,'O',5);
        testCasScore(t2,'X',2);
        testCasScore(t3,'O',2);
        testCasScore(t4,'X',7);
    }
    
    
    /** 
	teste un appel de estDiviseur
	@param x,y valeur des coordonnées
	@param t correspond au plateau de jeu
	@param joueur correspond au symbole placé sur le plateau
	@param result resultat attendu
	*/
	void testCasScore (char[][]t,char signe,int result) {// Affichage
		System.out.print ("score (");
		displayTabTest(t);
		System.out.print(","+signe+") \t= " + result + "\t : ");// Appel
		int resExec=score(t,signe);// Verification
		if (resExec == result){
			System.out.println ("OK");
		} else {
			System.err.println ("ERREUR");
		}
	}
		
		
	
	void displayTabTest(char[][] t){
        System.out.print("{");
        for(int i = 0; i<t.length; i++){
            System.out.print("{");
            for(int j = 0; j<t.length-1; j++){
                System.out.print(t[i][j] + ",");
            }
            System.out.print(t[i][t.length-1]+"}");
            if(i != t.length-1){
				System.out.print(",");
			}
        }
        System.out.print("}");
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
		System.out.println("Nombre de pionts sur le plateau : O = "+ score(t,'O') +" / X = "+ score(t,'X'));
		System.out.println("O : piont blanc / X : piont noir / ~ : case valide");
		
	}			
}

