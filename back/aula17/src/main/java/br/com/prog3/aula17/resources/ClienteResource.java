package br.com.prog3.aula17.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.prog3.aula17.domain.Cliente;
import br.com.prog3.aula17.service.ClienteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;
	@GetMapping("/clientes")
	public List<Cliente> getAllClientes() {
		List<Cliente> clientes = new ArrayList<>();
		clienteService.findAll().forEach(clientes::add);
		return clientes;
	}
	@PostMapping("/cliente")
	public Cliente postCliente(@RequestBody Cliente cliente) {
		Cliente _cliente = clienteService.save(new
				Cliente(cliente.getLogin(), cliente.getSenha()));
		return _cliente;
	}
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable("id")
	long id) {
		clienteService.deleteById(id);
		return new ResponseEntity<>("Cliente has been deleted!",HttpStatus.OK);
	}
	@GetMapping("clientes/senha/{senha}")
	public List<Cliente> findBySenha(@PathVariable int senha) {
		List<Cliente> cliente = clienteService.findBySenha(senha);
		return cliente;
	}
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id")
	long id, @RequestBody Cliente cliente) {
		Optional<Cliente> dadosCliente = clienteService.findById(id);
		if (dadosCliente.isPresent()) {
			Cliente _cliente = dadosCliente.get();
			_cliente.setLogin(cliente.getLogin());
			_cliente.setSenha(cliente.getSenha());
			_cliente.setRecebeProm(cliente.isRecebeProm());
			return new ResponseEntity<>(clienteService.save(_cliente),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}