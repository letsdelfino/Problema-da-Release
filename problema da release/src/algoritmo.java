import java.util.*; 

public class algoritmo {

  //criando variáveis auxiliares. Todas as variáveis nesse trecho do código serão usadas mais de uma vez. Para não cri´-las de novo aqui estão
  int i, j;
  
  private static final int cromossomo = 10; 
  private static final int n_releases = 3;
  private static final int populacao = 200;
  private static final int geracao = 100;
  private final double mutacao = 1;
  private static final double crossover = 0.003;
  
  public int[][] Fitness = new int[populacao][2];
  public int[][] individuo = new int[populacao][cromossomo];
  public int[][] proxGeracao = new int[populacao][cromossomo];
  public int[] probabilidade = new int [populacao]; // vai ajudar a determinar a possibilidade de um individuo ser escolhido
  
  Random valor = new Random();
  
  //criando poupulação aleatória
  
  public void genesis(){
    
    for (int i = 0; i < populacao; i++){
     for (int j = 0; j < cromossomo; j++) 
      individuo[i][j] = valor.nextInt(n_releases + 1); //.nextInt faz a leitura do próximo número digitado no console e atribui o número à variavél
    }
  }
    

  public void ordenar(){
  
    // a função em questão serve para ordenar os valores obtidos no cálculo do score
    int aux2, aux3;
    this.Avaliador();
    for (i = populacao - 1; i >= 1 ; i--){
      for (j = 1; j < i; j++){
      
        if (Fitness[i - 1][0] > Fitness[i][0]){
          
          // variáveis auxiliares que vão fazer guardar os valores para a troca de valores
          aux2 = Fitness[i][0];
          aux3 = Fitness[i][1];
          
          //substituição dos valore
          Fitness[i][0] = Fitness[i - 1][0];
          Fitness[i][1] = Fitness[i - 1][1];
          
          Fitness[i -1][0] = aux2;
          Fitness[i-1][1] = aux3;
         
        }
      }
    }
  
  }

    public void Avaliador() {
    
    	DNA novo = new DNA();
    	int Score;
    	int[] aux_temp = new int[cromossomo];
    	
    	for( i = 0; i < populacao; i++){
      
    		for(j = 0; j < cromossomo; j++){
        
    			aux_temp[j] = individuo[i][j];
          
        }
    		
    		Score = novo.fitness(aux_temp);
        
    		Fitness[i][0] = Score;
        
    		Fitness[i][1] = i;		
        
    	}
    }
    
      public int scoreFinal(){
      
    	int sFinal = 0; //variável que guarda o score final
    	
    	for(int i = 0; i < populacao; i++)
    		sFinal = Fitness[i][0] + sFinal;
    	
    	return sFinal;
      
    }
    
       public int scoreMedio(){
       
        int media;
        
        media = scoreFinal();
        
        return media/populacao;
      
    }
    
      public int selecaoRoleta(){
      
        int aux4 = 0;
        double num;
        
        num = Math.random(); //valor aleatório
        
        for(i = 0; i < populacao - 1; i++) {
        
          if(num > probabilidade[i] && num < probabilidade[i+1]) 
            aux4 = i;
            
        }
        
        return aux4;
        
      }
      
       public void Roleta(int total) {
        //dando a possibilidade de indivíduos serem selecionados usando a roleta
        
        this.ordenar();
        int ProbTotal = 0; //probabilidade total
        
        if(total == 0) {
        	
        	total =1;
        	
        }
        
        for(int i = 0; i < populacao; i++){
        
          probabilidade[i] = ProbTotal + Fitness[i][0] / total;
          ProbTotal = ProbTotal + probabilidade[i];

        }
    }
    
      public void Mutacao(int aux5) {
      
    	  int OutroNum;
    	  double num, valor2;
    	  
        do {
        	num = Math.random()*10;
        
        	valor2 = Math.round(num); //gerando valor aleatório com valor obtido anteriormente
        
        	OutroNum = (int)valor2;
        
        }while(OutroNum > 9);
        
        int valorTroca = valor.nextInt(n_releases + 1);

        proxGeracao[aux5][OutroNum] = valorTroca;
    	
    }
    
    public void crossoverFunct(){
    
    	int totalScore, num1, num2;
      
    	int [] vet1 = new int [cromossomo];
    	int [] vet2 = new int [cromossomo];
    	int [] filho1 = new int [cromossomo];
    	int [] filho2 = new int [cromossomo];
    	
      totalScore = this.scoreFinal();
      
      this.Roleta(totalScore);
      
      int aux6;
      Random cruz = new Random();
    	
      for(int i = 0; i < populacao ; i = i+2) {

  		aux6 = cruz.nextInt(100);

  		if(aux6 <= crossover) {

	    		num1 = this.selecaoRoleta();
		    	num2 = this.selecaoRoleta();
		    	
			double numero = Math.random()*10;
		    	double aleatorio = Math.round(numero);
		    	int fim = (int)aleatorio;
		    	
		    	for(int j = 0; j < cromossomo; j++) {
		    		vet1[j] = individuo[num1][j];
		    		vet2[j] = individuo[num2][j];
		    	}
		    	for(int j = 0; j < fim; j++) {
		    		filho1[j] = vet1[j];
		    		filho2[j] = vet2[j];
		    	}
		    	for(int j = fim; j < cromossomo; j++) {
		    		filho1[j] = vet2[j];
		    		filho2[j] = vet1[j];
		    	}
		    	
		    	for(int j = 0; j < cromossomo; j++) {
		    		proxGeracao[i][j] = filho1[j];
		    		proxGeracao[i+1][j] = filho1[j];
		    	}

		    	aux6 = cruz.nextInt(100);
		    	
		    	if(aux6 <= mutacao)
		    		this.Mutacao(i);
		    	
		    	aux6 = cruz.nextInt(100);
		    	
		    	if(aux6 <= mutacao)
			    	this.Mutacao(i+1);

		    }
    		else
    			i = i-2;
    	}
    	
    	for(int i = 0; i < populacao; i++) {
    		for(int j = 0; j < cromossomo; j++) {
    			individuo[i][j] = proxGeracao[i][j];
    		}
    	}	
    	this.ordenar();
    }
    
      public void Exibir() {
      
    	this.genesis();
    	
    	for(i = 0; i < geracao; i++) {
      
    		System.out.println("Media Score: " + this.scoreMedio());
    		System.out.println("Melhor: "  + this.Fitness[0][0]);
    		this.crossoverFunct();
        
    	}
    	
    	System.out.println("Melhor solucao: \n");
    	
    	for (int i = 0; i < cromossomo; i++) {
    		
    		System.out.println("" + individuo[0][i]);
    		
    	}
    }

}