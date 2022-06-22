package io.com.github.silvaalexsander.vendas;

import io.com.github.silvaalexsander.vendas.domain.entity.Cliente;
import io.com.github.silvaalexsander.vendas.domain.entity.repositoy.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			clientes.salvar(new Cliente("Alexsander"));
			clientes.salvar(new Cliente("Joao"));

			System.out.println("Salvando e exibindo pessoas no banco");
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("\nAtualizando e exibindo");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " Silva");
				clientes.atualizar(c);
			});
			todosClientes.forEach(System.out::println);

			System.out.println("\nBusca por nome e exibe");
			clientes.buscarPorNome("J").forEach(System.out::println);

			System.out.println("\nDeletando clientes");
			clientes.obterTodos().forEach(c -> {
				clientes.deletar(c);
			});

			todosClientes = clientes.obterTodos();
			if(todosClientes.isEmpty()){
				System.out.println("Nenhum cliente encontrado");
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
