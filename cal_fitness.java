package aptidao;

public class aptidao{
	
	//criando variáveis de informações do problema
	private int n_req; //número de requisitos do problema
	private int n_cli; // número de clientes
	private int n_release //número de releases
	private double orcamento; //definindo orçamento
	
	//criando as variáveis auxiliares do problema. É possível criar isso globalmente e chamar apenas uma vez? VER ISSO MAIS TARDE
	public int i,j; //necessários no laço de repetição
	public int score; //socre do indivíduo
	public int alocado; //variável que vai verificar se o requisito foi alocado na release
	public int verificador; //vai verificar se o requisito foi alocado na release
	  
	//inicializa as variáveis  
    public int[] importancia = new int[n_req];
    public int[] relev_cli = new int[n_cli];//relevância do cliente.
    public int[][] valor_relev = new int[n_cli][n_req];//determina a importância que cliente estabelece para o requisito
    public int[] risco = new int[n_req];
    public double[] custo = new double[n_req];
    public int[] solucao = new int[n_req];//solução

	public void setN_req(int n_req) {
        n_req = n_req;
    }

    public int getN_req(int n_req) {
        return n_req;
    }

    public void setN_cli(int n_cli) {
        n_cli = n_cli;
    }

    public int getN_cli(int n_cli) {
        return n_cli;
    }

    public void setN_release(int n_release) {
        n_release = n_release;
    }

    public int getN_release(int n_release) {
        return n_release;
    }

    public void setOrcamento(double orcamento) {
        orcamento = orcamento;
    }

    public double getOrcamento(double orcamento) {
        return orcamento;
    }
	
		//iniciando atributos com informações dadas no documernto enviado pelo professor. Criando método para inicianlizar os atributos. Iniciando cálculo de aptidão do indivíduo
	public CalcularApt(){
		
		this.n_req = 10;
		this.n_cli = 3;
		this.orcamento = 125;
		this.n_release = 3;
		
		/*
		Inicializando a relevância do cliente
		Cliente 1: 3
		Cliente 2: 4
		Cliente 3: 2
		*/
		this.relev_cli[] = new int[] {3, 4, 2};
		
		//adicionando importância do requisito. Inicializando vetor de dados
		/*
		
		Efetuando Pedido {10, 10, 5}
		Busca Produtos {8, 10, 6}
		Sugestão de Estabelecimentos Próximos {6, 4, 8}
        Definir várias Formas de Pagamento {5, 9, 1} 
		Compartilhar nas Redes Sociais {7, 7, 5} 
		Emitir Feedback sobre aplicativo {8, 6, 2}
        Login através de Redes Sociais {6, 6, 4} 
		Executar em Múltiplas Plataformas {9, 8, 3} 
		Sistema de Recomendação {6, 7, 5} 
		Cadastro de Produtos {10, 10, 7}}
		*/	
		this.valor_relev = new int[][] {{10, 10, 5}, 
		{8, 10, 6}, 
		{6, 4, 8},
        {5, 9, 1}, 
		{7, 7, 5}, 
		{8, 6, 2},
        {6, 6, 4}, 
		{9, 8, 3}, 
		{6, 7, 5}, 
		{10, 10, 7}};
		
		//inicializa o valor do risco
		/*
		Efetuando Pedido 4
		Busca Produtos 6
		Sugestão de Estabelecimentos Próximos 2
        Definir várias Formas de Pagamento 6 
		Compartilhar nas Redes Sociais 4 
		Emitir Feedback sobre aplicativo 8
        Login através de Redes Sociais 9 
		Executar em Múltiplas Plataformas 7 
		Sistema de Recomendação 6 
		Cadastro de Produtos 6
		*/
		this.risco = new int[] {3, 6, 2, 6, 4, 8, 9, 7, 6, 6};
		
		/*Inicializando o custo
		Efetuando Pedido 60
		Busca Produtos 40
		Sugestão de Estabelecimentos Próximos 40
        Definir várias Formas de Pagamento 30 
		Compartilhar nas Redes Sociais 20 
		Emitir Feedback sobre aplicativo 20
        Login através de Redes Sociais 25 
		Executar em Múltiplas Plataformas 70 
		Sistema de Recomendação 50 
		Cadastro de Produtos 20
		*/
		this.custo[] = new double[] 60.0, 40.0, 40.0, 30.0, 20.0, 
		20.0, 25.0, 70.0, 50.0, 20.0};
		
	}
	
		//iniciando cálculo do fitness
	public int fitness(int[] solucao) {
		//calculando importância do cliente
		for (i = 0; i <= n_req; i++) {
			importancia[i] = 0;
			for (j = 0; j <= n_cli; j++) {
				importancia[i] = importancia[i] + (relev_cli[j] * valor_relev[i][j]);
			}
		}
	
	    //calculando score
        for (i = 0; i <= n_req; i++) {
            score = 0;
            if (solucao[i] == 0)
                alocado = 0;
			else
                alocado = 1;
            score = score + ((importancia[i] * (n_release - solucao[i] + 1)) - (risco[i] * solucao[i]) * alocado);
        }
		

	
}
