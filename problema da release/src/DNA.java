public class DNA{

	//criando as vari�veis auxiliares do problema. � poss�vel criar isso globalmente e chamar apenas uma vez? VER ISSO MAIS TARDE
	public int i, j; //necess�rios no la�o de repeti��o
	public int cromossomo_tam; //tamanho do cromossomo
	public double[] fitness;
	public int cromossomo;
	public int score = 0;
	int fiq;
	public int alocado; //vari�vel que vai verificar se o requisito foi alocado na release
	public int verificador; //vai verificar se o requisito foi alocado na release
	double custoFinal = 0.0;
	int penalizacao;
	
	//criando vari�veis de informa��es do problema
	private int n_req = 10; //n�mero de requisitos do problema
	private int n_cli = 3; // n�mero de clientes
	private int n_release = 3; //n�mero de releases
	private double orcamento = 125; //definindo or�amento
	
	//adicionando import�ncia do requisito. Inicializando vetor de dados
		/*
		
		Efetuando Pedido {10, 10, 5}
		Busca Produtos {8, 10, 6}
		Sugest�o de Estabelecimentos Pr�ximos {6, 4, 8}
        Definir v�rias Formas de Pagamento {5, 9, 1} 
		Compartilhar nas Redes Sociais {7, 7, 5} 
		Emitir Feedback sobre aplicativo {8, 6, 2}
        Login atrav�s de Redes Sociais {6, 6, 4} 
		Executar em M�ltiplas Plataformas {9, 8, 3} 
		Sistema de Recomenda��o {6, 7, 5} 
		Cadastro de Produtos {10, 10, 7}}
		*/	
		public int valor_relev[][] = {{10, 10, 5}, 
		{8, 10, 6}, {6, 4, 8}, {5, 9, 1}, {7, 7, 5}, 
		{8, 6, 2}, {6, 6, 4}, {9, 8, 3}, 
		{6, 7, 5}, {10, 10, 7}};	
	//
	//Inicializando a relev�ncia do cliente
	//	Cliente 1: 3
	//	Cliente 2: 4
	//	Cliente 3: 2
	//
	public int[] relev_cli = {3,4,2};
		
	//inicializa o valor do risco
		/*
		Efetuando Pedido 4
		Busca Produtos 6
		Sugest�o de Estabelecimentos Pr�ximos 2
        Definir v�rias Formas de Pagamento 6 
		Compartilhar nas Redes Sociais 4 
		Emitir Feedback sobre aplicativo 8
        Login atrav�s de Redes Sociais 9 
		Executar em M�ltiplas Plataformas 7 
		Sistema de Recomenda��o 6 
		Cadastro de Produtos 6
		*/
	
		public int[] risco = {3, 6, 2, 6, 4, 8, 9, 7, 6, 6};
		
		/*Inicializando o custo
		Efetuando Pedido 60
		Busca Produtos 40
		Sugest�o de Estabelecimentos Pr�ximos 40
        Definir v�rias Formas de Pagamento 30 
		Compartilhar nas Redes Sociais 20 
		Emitir Feedback sobre aplicativo 20
        Login atrav�s de Redes Sociais 25 
		Executar em M�ltiplas Plataformas 70 
		Sistema de Recomenda��o 50 
		Cadastro de Produtos 20
		*/
		public double[] custo = {60.0, 40.0, 40.0, 30.0, 20.0, 20.0, 25.0, 70.0, 50.0, 20.0};
		
		public int[] importancia = new int[n_req]; //import�nica total dos requisitos
	
	//iniciando c�lculo do fitness
	public int fitness(int[] solucao) {
			
		//calculando import�ncia do requisito
		for (int i = 0; i < n_req; i++) {
			importancia[i] = 0;
			for (int j = 0; j < n_cli; j++) {
				importancia[i] = importancia[i] + (relev_cli[j]*valor_relev[i][j]);
			}
		}
	
	    //calculando score
		for (i = 0; i < n_req; i++) {
		    if (solucao[i] == 0)
			alocado = 0;
				else
			alocado = 1;
		    score = score + ((importancia[i] * (n_release - solucao[i] + 1)) - (risco[i] * solucao[i]) * alocado);
		}
	
		//verifica se foi alocado na release
		for (i = 0; i < n_req; i++){
			if (solucao[i] != 0)
				fiq = 1;
			else
				fiq = 0;

			custoFinal = custoFinal + custo[i]*fiq;
			
	}
		
		if (custoFinal > orcamento){
		
			penalizacao = (int)custoFinal - (int)orcamento;
			
			return score/penalizacao;
			
		}
			
		else 
			return score;
	}
	
		//calculando desvio do curso das release
	  public int calcularDesvio(int [] solucao){
		  
	    	double [] custoTotalRelease_n = new double [n_release];
	    	double desvio=0.0;
	    	for(int i = 0; i < n_release; i++)
	    		custoTotalRelease_n[i] = 0.0;
	    	
	    	for(int i = 0; i < n_req; i++) {
	    		if(solucao[i] == 1)
	    			custoTotalRelease_n[0] = custoTotalRelease_n[0] + custoTotalRelease_n[i];
	    		
	    		if(solucao[i] == 2)
	    			custoTotalRelease_n[1] = custoTotalRelease_n[1] + custoTotalRelease_n[i];
	    		
	    		if(solucao[i] == 3)
	    			custoTotalRelease_n[2] = custoTotalRelease_n[2] + custoTotalRelease_n[i];
	    	}
	    	
	    	for(int i = 0; i < n_release; i++) {
	    		if(custoTotalRelease_n[i] > orcamento)
	    			desvio = desvio + custoTotalRelease_n[i] - orcamento;
	    	}
	    	return (int)desvio;
	    }
 
	  public boolean VerificarSolucao(int [] solucao){
	    	double [] custoTotalRelease_n = new double [n_release];
	    	int sai = 0;
	    	
	    	for(int i = 0; i < n_release; i++)
	    		custoTotalRelease_n[i] = 0.0;
	    	
	    	for(int i = 0; i < n_release; i++) {
	    		if(solucao[i] == 1)
	    			custoTotalRelease_n[0] = custoTotalRelease_n[0] + custoTotalRelease_n[i];
	    		
	    		if(solucao[i] == 2)
	    			custoTotalRelease_n[1] = custoTotalRelease_n[1] + custoTotalRelease_n[i];
	    		
	    		if(solucao[i] == 3)
	    			custoTotalRelease_n[2] = custoTotalRelease_n[2] + custoTotalRelease_n[i];
	    	}
	    	
	    	for(int i = 0; i < n_release; i++) {
	    		if(custoTotalRelease_n[i] > orcamento)
	    			sai = 1;
	    	}
	    	
	    	if(sai == 0)
	    		return true;	
	    	else
	    		return false;
	    }
}