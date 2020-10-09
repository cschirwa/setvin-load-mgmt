package za.co.setvin.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.setvin.entity.Country;
import za.co.setvin.entity.Currency;
import za.co.setvin.entity.Customer;
import za.co.setvin.entity.Driver;
import za.co.setvin.entity.Load;
import za.co.setvin.entity.Status;
import za.co.setvin.entity.Truck;
import za.co.setvin.repository.CountryRepository;
import za.co.setvin.service.CurrencyService;
import za.co.setvin.service.CustomerService;
import za.co.setvin.service.DriverService;
import za.co.setvin.service.LoadService;
import za.co.setvin.service.StatusService;
import za.co.setvin.service.TruckService;

@Component
public class Bootstrap {
	
	private CustomerService customerService;
	
	private LoadService loadService;
	
	private CurrencyService currencyService;
	
	private CountryRepository countryService;
	
	private StatusService statusService;
	
	private DriverService driverService;
	
	private TruckService truckService;
	
	
	@Autowired
	public Bootstrap(CustomerService customerService, 
			LoadService loadService, 
			CurrencyService currencyService,
			CountryRepository countryService,
			StatusService statusService,
			DriverService driverService,
			TruckService truckService) {
		this.customerService = customerService;
		this.loadService = loadService;
		this.currencyService = currencyService;
		this.countryService = countryService;
		this.statusService = statusService;
		this.driverService = driverService;
		this.truckService = truckService;
		loadStatuses();
		loadCurrencies();
		loadCountries();
		loadCustomers();
		loadTrucks();
		loadDrivers();
		loadTruckLoads();
	}
	
	private void loadTrucks() {
		List<Truck> trucks = Arrays.asList(
				new Truck("FB00HL-GP","Volvo","FH440","VHKT123456789543-GH"),
				new Truck("FB01HL-GP","Volvo","FH440","VHKT123456a12389-GH"),
				new Truck("FB02HL-GP","Mercedez","Actros","MB123456789543-GH","MB5678123123123123"),
				new Truck("FB03HL-GP","Mercedez","Actros","MBKT123456789543-GH","mb567891231231232")
				);
		trucks.forEach(truck -> truckService.add(truck));
	}

	private void loadStatuses() {
		List<Status> statuses = Arrays.asList(
				new Status("TO_BE_LOADED", "To Be Loaded"),
				new Status("LOADING", "Loading"),
				new Status("IN_TRANSIT", "In Transit"),
				new Status("DELAYED", "Delayed"),
				new Status("DELIVERED", "Delivered")
				
				);
		statuses.forEach(status -> statusService.save(status));
	}
	
	private void loadCustomers() {
		Customer customer = new Customer();
		customer.setName("Setvin");
		customer.setContactPerson("Calvin");
		customer.setEmail("calvin@setvin.com");
		customer.setCountry("South Africa");
		customer.setDefaultCcy("ZAR");
		customer.setPhone("0784567890");
		customer.setVatNumber("4111222333");
		customer.setEnterpriseNumber("2010/123456/78");
		customer.setAddress("7 The Algarve, Paulshof 2191");
		customer.setBalance(new BigDecimal(50000));
		customerService.add(customer);
	}
	
	private void loadDrivers() {
		Truck truck = truckService.findByRegistration("FB00HL-GP");
		Driver driver = new Driver();
		driver.setFirstname("Kuziwa");
		driver.setLastname("Matika");
		driver.setDob(LocalDate.of(1991, 04, 23));
		driver.setIdNumber("7509026128188");
		driver.setLicenseNumber("12312312333");
		driver.setLicenseExpiry(LocalDate.now());
		driver.setMedicalsExpiry(LocalDate.now());
		driver.setNationality("Zimbabwe");
		driver.setCurrentTruck(truck);
		driver.setNickname("GREENE");
		driver.setPassportNumber("FN123456");
		driverService.add(driver);
	}
	
	private void loadTruckLoads() {
		Load load = Load.builder()
				.customer(customerService.findCustomer(1L))
				.orderNumber("12345")
				.description("Load to Durban - Manganese 30MT")
				.instructions("Load by 8am, offload by 3pm")
				.loadDate(LocalDate.now())
				.deliveryDate(LocalDate.now())
				.fromAddress("Johannesburg")
				.fromSuburb("City Deep")
				.fromCountry("South Africa")
				.toAddress("Durban")
				.toSuburb("Umshaka")
				.toCountry("South Africa")
				.quantity(34)
				.rate(new BigDecimal(1000))
				.totalAmount(new BigDecimal(34000))
				.chargeVat(true)
				.currency("ZAR")
				.status(statusService.findByName("IN_TRANSIT"))
				.driver(driverService.findByFirstName("Kuziwa"))
				.truck(truckService.findByRegistration("FB00HL-GP"))
				.build();
		loadService.add(load);
	}
	
	private void loadCurrencies() {
		List<Currency> currencies = Arrays.asList(
				new Currency("ZAR", "Rand"),
				new Currency("BWP", "Pula"),
				new Currency("GBP", "Pound"),
				new Currency("USD", "Dollar")
				);
		currencies.forEach(ccy -> currencyService.add(ccy));
	}
	
	private void loadCountries() {
		List<Country> countries = Arrays.asList(
				new Country("ZA", "South Africa", currencyService.findByIsoCode("ZAR")),
				new Country("BW", "Botswana", currencyService.findByIsoCode("BWP")),
				new Country("MZ", "Mozambique", currencyService.findByIsoCode("USD")),
				new Country("NM", "Namibia", currencyService.findByIsoCode("ZAR")),
				new Country("ZM", "Zambia", currencyService.findByIsoCode("USD")),
				new Country("ZW", "Zimbabwe", currencyService.findByIsoCode("USD"))
				);
		countries.forEach(country -> countryService.save(country));
	}

}
