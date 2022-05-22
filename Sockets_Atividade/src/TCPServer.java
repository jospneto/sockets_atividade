import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class TCPServer {
	public static void pesquisarDados(String valor, String dados[]) {
		String mensagem = "";
		for(int i = 0; i <= dados.length; i++) {
			String dado = dados[i].substring(i);
			if(valor.equalsIgnoreCase(dado)) {
				mensagem = dado;
			}else {
				mensagem = "Cliente não encontrado no banco de dados";
			}
		}
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("+------------------------+");
		System.out.println("Servidor TCP foi iniciado!");
		System.out.println("+------------------------+");
		
		int port = 4500;
		
		ServerSocket socketServidor = new ServerSocket(port);
		Socket socketClient = socketServidor.accept();
		
		PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		FileWriter dados = new FileWriter("registro.txt");
		
		String entrada[] = new String[3];
		
		for(int i = 0; i <= entrada.length; i++) {
			if((entrada[i] = in.readLine()) != null) {
				System.out.println("Dados do cliente: " + entrada[i]);
				out.println(entrada[i]);
				dados.write("Dados do cliente: " + entrada[i].toString() + LocalDateTime.now().toString() +"\n");
			}
			pesquisarDados(in.readLine(), entrada);
		}
		
		dados.close();
		out.close();
		in.close();
		socketClient.close();
		socketServidor.close();
		
		System.out.println("\nO cliente digitou SAIR...");
		System.out.println("\n_________________________");
		System.out.println("\nConexão encerrada...");
	}
}
