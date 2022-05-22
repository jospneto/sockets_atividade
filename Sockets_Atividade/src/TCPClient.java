import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class TCPClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		int port = 4500;
		Socket socket = new Socket("localhost", port);
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
		String entradaServidor = "";
		String entradaUsuario = "";
		String opcao = JOptionPane.showInputDialog(null, "Digite sua opção: 1 - Inserir valores \n 2 - Pesquisar"
				+ "\n 3 - Sair");
		/*String entradaUsuario = JOptionPane.showInputDialog(null, "Digite os seguintes dados: \n Nome - Valor do abastecimento - "
				+ "Tipo de combustível: ");*/
		
		while(!opcao.equalsIgnoreCase("3")) {
			if(opcao.equalsIgnoreCase("1")) {
				entradaUsuario = JOptionPane.showInputDialog(null, "Digite os seguintes dados: \n Nome do cliente - "
						+ "Valor do abastecimento - "
						+ "Tipo de combustível: ");
				out.println(entradaUsuario);
				entradaServidor = in.readLine();
			}else if(opcao.equalsIgnoreCase("2")){
				entradaUsuario = JOptionPane.showInputDialog(null, "Digite o nome do cliente: ");
				entradaServidor = in.readLine();
			}else {
				JOptionPane.showMessageDialog(null, "Saindo do programa...");
				break;
			}
		}
		
		out.close();
		in.close();
		socket.close();
	}
}
