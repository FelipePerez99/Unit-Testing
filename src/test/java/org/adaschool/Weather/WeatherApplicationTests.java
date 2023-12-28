package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(classes = MockitoExtension.class)
class WeatherApplicationTests {

	private double latitud = 39.478;
	private double longitud = -90.000;

	@Test
	void contextLoads() {
	}

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private WeatherReportService weatherReportService;

	@InjectMocks
	private WeatherReportController weatherReportController;

	@Test
	public void testWeatherReportController(){
		WeatherReport weatherReport = new WeatherReport();
		Mockito.when(weatherReportService.getWeatherReport(latitud, longitud)).
				thenReturn(weatherReport);

		WeatherReport resultado = weatherReportController.getWeatherReport(latitud, longitud);
		Assertions.assertNotNull(resultado);
	}

	@Test
	public void testWeatherReportService() {
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitud + "&lon=" + longitud + "&appid="
				+ weatherReportService.getApiKey();

		Mockito.when(restTemplate.getForObject(apiUrl, WeatherApiResponse.class)).thenReturn(null);

		WeatherReport resultado = weatherReportService.getWeatherReport(latitud, longitud);

		Assertions.assertNull(resultado, "Se espera un resultado nulo ");
	}


}
