package com.acme.fuel.service.factory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.acme.fuel.model.Consumption;

@Component
public class ConsumptionListFactory {

	public List<Consumption> create(MultipartFile csvFile) throws IOException {
		String stringFile = new String(csvFile.getBytes());
		String[] lines = stringFile.split("\n");
		List<Consumption> product = new ArrayList<>();
		
		for (String line : lines) {
			product.add(lineToObject(line));
		}
		return product;
	}

	private Consumption lineToObject(String line) {
		String[] obj = line.split(";");
		return Consumption.builder()
				.fuelType(obj[0])
				.volumeInLitters(Long.parseLong(obj[1]))
				.pricePerLitter(Long.parseLong(obj[2]))
				.date(LocalDateTime.parse(obj[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME))
				.driverId(Long.parseLong(obj[4]))
				.build();
	}

}
