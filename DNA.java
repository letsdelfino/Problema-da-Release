package aptidao;
import java.util.Random

public class DNA{

	//criando as variáveis auxiliares do problema. É possível criar isso globalmente e chamar apenas uma vez? VER ISSO MAIS TARDE
	public i,j; //necessários no laço de repetição
	public int cromossomo_tam; //tamanho do cromossomo
	public double[] fitness;
	public int cromossomo;
	public int score = 0;
	int fiq;
	public int alocado; //variável que vai verificar se o requisito foi alocado na release
	public int verificador; //vai verificar se o requisito foi alocado na release
	double custoFinal = 0.0;
	int penalizacao;
	
	//criando variáveis de informações do problema
	private int n_req = 10; //número de requisitos do problema
	private int n_cli = 3; // número de clientes
	private int n_release = 3 //número de releases
	private double orcamento = 125; //definindo orçamento
	
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
		{8, 10, 6}, {6, 4, 8}, {5, 9, 1}, {7, 7, 5}, 
		{8, 6, 2}, {6, 6, 4}, {9, 8, 3}, 
		{6, 7, 5}, {10, 10, 7}};	
	/*
	Inicializando a relevância do cliente
		Cliente 1: 3
		Cliente 2: 4
		Cliente 3: 2
	*/
		this.relev_cli[] = new int[] {3, 4, 2};
		
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
		
		public int[] importancia = new int[n_req] //importânica total dos requisitos
	
	//iniciando cálculo do fitness
	public int fitness(int[] solucao) {
			
		//calculando importância do requisito
		for (i = 0; i <= n_req; i++) {
			importancia[i] = 0;
			for (j = 0; j <= n_cli; j++) {
				importancia[i] = importancia[i] + (relev_cli[j] * valor_relev[i][j]);
			}
		}
	
	    //calculando score
		for (i = 0; i <= n_req; i++) {
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

			custoFianl = custoFianl + custo[i]*fiq;
			
	}
		
		if custoFinal > orcamento){
		
			penalizacao = (int)custoFianl - (int)orcamento;
			
			return score/penalizacao;
			
		}
			
		else 
			return score;
	
}
