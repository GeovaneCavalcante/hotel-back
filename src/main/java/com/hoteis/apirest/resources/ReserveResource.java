package com.hoteis.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoteis.apirest.models.Reserve;
import com.hoteis.apirest.repository.ReserveRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ReserveResource {
	@Autowired
	ReserveRepository reserveRepository;

	@GetMapping("/reservations")
	@ApiOperation(value = "Retorna uma lista de reservas")
	public List<Reserve> listHoteis() {

		return reserveRepository.findAll();
	}

	@GetMapping("/reservations/{id}")
	@ApiOperation(value = "Retorna uma reserva")
	public Reserve listReserve(@PathVariable(value = "id") long id) {
		return reserveRepository.findById(id);
	}
	
	@GetMapping("/reservations/user/{id}")
	@ApiOperation(value = "Retorna as reservas de usuários")
	public List<Reserve> listReserveUser(@PathVariable(value = "id") long id) {
			
		return reserveRepository.findAllByCurrentUser(id);
	}

	@PostMapping("/reservations")
	@ApiOperation(value = "Cria uma reserva")
	public Reserve storeReserve(@RequestBody Reserve reserve) {
		return reserveRepository.save(reserve);
	}

	@DeleteMapping("/reservations")
	@ApiOperation(value = "Deleta uma reserva")
	public void deleteReserve(@RequestBody Reserve reserve) {
		reserveRepository.delete(reserve);
	}

	@PutMapping("/reservations")
	@ApiOperation(value = "Atualiza uma reserva")
	public Reserve updateReserve(@RequestBody Reserve reserve) {
		return reserveRepository.save(reserve);
	}
}
