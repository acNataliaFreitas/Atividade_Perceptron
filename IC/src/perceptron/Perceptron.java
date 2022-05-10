package perceptron;

import java.util.Scanner;

public class Perceptron {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		boolean gerarNovamente = true;

		while (gerarNovamente) {

			int alfaT = 1;
			int limiar = 1;

			int Yent = 0;
			int geracao = 0;
			int[] pesos = { 0, 0, 0, 0 };
			boolean PesoVariacao = true;

			int[][] tabelaVerdade = { { 1, 1, 1, 1 }, { 1, 1, -1, 1 }, { 1, -1, 1, 1 }, { 1, -1, -1, 1 },
					{ -1, 1, 1, 1 }, { -1, 1, -1, 1 }, { -1, -1, 1, 1 }, { -1, -1, -1, 1 } };

			int[] targetAnd = { 1, -1, -1, -1, -1, -1, -1, -1 };
			int[] targetOr = { 1, 1, 1, 1, 1, 1, 1, -1 };
			int[] targetXor = { 0, 1, 1, 0, 1, 0, 0, 0 };

			int[] target = new int[8];
			int leitura;

			boolean Validacao = false;
			System.out.println();
			System.out.print("-------------------- PERCEPTRON --------------------");
			System.out.println();

			do {
				System.out.print("Informe a Operação: 1)AND 2)OR 3)XOR ");
				leitura = sc.nextInt();
				System.out.println();

				if (leitura == 1) {
					target = targetAnd;
					Validacao = true;
				}

				else if (leitura == 2) {
					target = targetOr;
					Validacao = true;
				}

				else if (leitura == 3) {
					target = targetXor;
					Validacao = true;
				} else {
					System.out.print("Operação Inválida! Tente Novamente.");
				}
			}

			while (!Validacao);

			while (PesoVariacao) {
				int[] allYent = new int[8];
				int[] allFYent = new int[8];

				int[] allWeights = new int[8];
				int[][] variacaoPeso = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
						{ 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

				geracao += 1;

				for (int linha = 0; linha < 8; linha++) {
					int yent = 0;

					for (int coluna = 0; coluna < 4; coluna++) {
						yent += tabelaVerdade[linha][coluna] * pesos[coluna];
					}

					if (yent > limiar) {
						Yent = 1;
					} else if (yent <= limiar && yent >= -limiar) {
						Yent = 0;
					} else {
						Yent = -1;
					}

					int variacao = alfaT * (target[linha] - Yent);

					variacaoPeso[linha][0] = variacao * tabelaVerdade[linha][0];
					variacaoPeso[linha][1] = variacao * tabelaVerdade[linha][1];
					variacaoPeso[linha][2] = variacao * tabelaVerdade[linha][2];
					variacaoPeso[linha][3] = variacao * tabelaVerdade[linha][3];

					pesos[0] += variacaoPeso[linha][0];
					pesos[1] += variacaoPeso[linha][1];
					pesos[2] += variacaoPeso[linha][2];
					pesos[3] += variacaoPeso[linha][3];

					allYent[linha] = yent;
					allFYent[linha] = Yent;
					allWeights = pesos;
				}

				System.out.println("Epoca: " + geracao);
				System.out.println();
				System.out.println();
				int count = 1;
				System.out.print("Yent: [");
				count = 1;
				for (int fYent : allYent) {
					if (count == 8) {
						System.out.print(Yent);
					} else {
						System.out.print(Yent + ", ");
					}
					count++;
				}
				System.out.print("] ");
				System.out.println();
				System.out.println();
				System.out.print("FYent: [");
				count = 1;
				for (int FYent : allFYent) {
					if (count == 8) {
						System.out.print(FYent);
					} else {
						System.out.print(FYent + ", ");
					}
					count++;
				}
				System.out.print("] ");
				System.out.println();
				System.out.println();
				System.out.print("Pesos: ");
				System.out.print("[");
				count = 1;
				for (int peso : allWeights) {
					if (count == 4) {
						System.out.print(peso);
					} else {
						System.out.print(peso + ", ");
					}
					count++;
				}
				System.out.print("] ");
				System.out.println();
				System.out.println("------------------------------------------------");

				boolean Resultado = pesoVaria(variacaoPeso);

				if (geracao == 100 && leitura == 3) {
					PesoVariacao = false;
					System.out.println("");
					System.out.println("Utilizando a operação XOR o looping se tornou infinito!");
				}

				if (Resultado) {
					PesoVariacao = false;
				}
			}

			boolean opcaoValida = false;

			do {
				System.out.println();
				System.out.print("Deseja Rodar Novamente(S / N)? ");
				String rodar = sc.nextLine();
				System.out.println();

				if (rodar.equalsIgnoreCase("S") || rodar.equalsIgnoreCase("N")) {
					opcaoValida = true;
				}

				else if (rodar.equalsIgnoreCase("N")) {
					gerarNovamente = false;
					break;
				}

			} while (!opcaoValida);

		}
		sc.close();
	}

	public static boolean pesoVaria(int[][] variacaoPeso) {
		boolean done = true;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 4; j++) {
				if (variacaoPeso[i][j] != 0) {
					done = false;
					break;
				}
			}

			if (!done)
				break;
		}

		return done;
	}
}
