package com.hoteis.apirest.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoteis.apirest.models.Hotel;
import com.hoteis.apirest.repository.HotelRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST HOTEIS")
@CrossOrigin(origins = "*")
public class HotelResource {

	@Autowired
	HotelRepository hotelRepository;

	@GetMapping("/hoteis")
	@ApiOperation(value = "Retorna uma lista de hoteis")
	public List<Hotel> listHoteis(@RequestParam(required = false) String title, String startDateAvailable,
			String endDateAvailable) throws ParseException {

		if (title != null && startDateAvailable != null && endDateAvailable != null) {

			title = title.replaceAll("\"", "");
			startDateAvailable = startDateAvailable.replaceAll("\"", "");
			endDateAvailable = endDateAvailable.replaceAll("\"", "");

			Date dateStart = (Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) startDateAvailable);
			Date dateEnd = (Date) new SimpleDateFormat("yyyy-MM-dd").parse((String) endDateAvailable);
			return hotelRepository.findByAutocomplete(title.toLowerCase(), dateStart, dateEnd);

		} else if (title != null && startDateAvailable == null && endDateAvailable == null) {

			title = title.replaceAll("\"", "");

			return hotelRepository.findByTitleContainingIgnoreCase(title);
		}

		return hotelRepository.findAllByOrderByPriceAsc();
	}

	@GetMapping("/hoteis/{id}")
	@ApiOperation(value = "Retorna um hotel")
	public Hotel listHotel(@PathVariable(value = "id") long id) {
		return hotelRepository.findById(id);
	}

	@PostMapping("/hoteis")
	@ApiOperation(value = "Cria um hotel")
	public Hotel storeHotel(@RequestBody Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@DeleteMapping("/hoteis")
	@ApiOperation(value = "Deleta um hotel")
	public void deleteHotel(@RequestBody Hotel hotel) {
		hotelRepository.delete(hotel);
	}

	@PutMapping("/hoteis")
	@ApiOperation(value = "Atualiza um hotel")
	public Hotel updateHotel(@RequestBody Hotel hotel) {
		return hotelRepository.save(hotel);
	}

}
