package za.co.setvin.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import za.co.setvin.entity.Country;
import za.co.setvin.entity.Currency;
import za.co.setvin.entity.Customer;
import za.co.setvin.entity.Driver;
import za.co.setvin.entity.Load;
import za.co.setvin.entity.Status;
import za.co.setvin.entity.Supplier;
import za.co.setvin.entity.Truck;
import za.co.setvin.entity.User;
import za.co.setvin.repository.CountryRepository;
import za.co.setvin.repository.UserRepository;
import za.co.setvin.service.CurrencyService;
import za.co.setvin.service.CustomerService;
import za.co.setvin.service.DriverService;
import za.co.setvin.service.LoadService;
import za.co.setvin.service.StatusService;
import za.co.setvin.service.SupplierService;
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
	
	private SupplierService supplierService;
	
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;

	@Autowired
	public Bootstrap(CustomerService customerService, LoadService loadService, CurrencyService currencyService,
			CountryRepository countryService, StatusService statusService, DriverService driverService,
			TruckService truckService, SupplierService supplierService, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.customerService = customerService;
		this.loadService = loadService;
		this.currencyService = currencyService;
		this.countryService = countryService;
		this.statusService = statusService;
		this.driverService = driverService;
		this.truckService = truckService;
		this.supplierService = supplierService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		loadStatuses();
		loadCurrencies();
		loadCountries();
		loadCustomers();
		loadTrucks();
		loadDrivers();
		loadTruckLoads();
		loadSuppliers();
		loadUser();
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
		
		Customer customer2 = new Customer();
		customer2.setName("Deferro");
		customer2.setContactPerson("Deferro");
		customer2.setEmail("calvin@deferro.com");
		customer2.setCountry("Zimbabwe");
		customer2.setDefaultCcy("ZAR");
		customer2.setPhone("0876767676");
		customer2.setVatNumber("4111444333");
		customer2.setEnterpriseNumber("2010/123456/80");
		customer2.setAddress("Mutare, 020");
		customer2.setBalance(new BigDecimal(89000));
		customerService.add(customer2);
	}
	
	private void loadDrivers() {
		Truck truck = truckService.findByRegistration("FB00HL-GP");
		Driver driver = new Driver();
		driver.setFirstname("Kuziwa");
		driver.setLastname("Matika");
		driver.setDob(LocalDate.of(1991, 04, 23));
		driver.setIdNumber("7509026128188");
		driver.setLicenceNumber("12312312333");
		driver.setLicenceExpiry(LocalDate.now());
		driver.setMedicalsExpiry(LocalDate.now());
		driver.setNationality("Zimbabwe");
		driver.setCurrentTruck(truck);
		driver.setNickname("GREENE");
		driver.setPassportNumber("FN123456");
		driverService.add(driver);
	}
	
	private void loadTruckLoads() {
		Load load = Load.builder()
				.customer(customerService.findCustomer(1L).getName())
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
				.status(statusService.findByName("IN_TRANSIT").getNarration())
				.driver(driverService.findByFirstName("Kuziwa").getFirstname() + " " + driverService.findByFirstName("Kuziwa").getLastname())
				.truck(truckService.findByRegistration("FB00HL-GP").getRegistration())
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
	
	private void loadSuppliers() {
		Supplier supp = new Supplier();
		supp.setName("Cartwright");
		supp.setCreated(LocalDateTime.now());
		supp.setInvoiceDate(LocalDate.now());
		supp.setPhone("087878787877");
		supp.setEmail("c@cartwright.com");
		supp.setContact("Rickie Fowler");
		supplierService.add(supp);
		
		Supplier supp2 = new Supplier();
		supp2.setName("Estado Natural");
		supp2.setCreated(LocalDateTime.now());
		supp2.setInvoiceDate(LocalDate.now());
		supp2.setPhone("0334455877");
		supp2.setEmail("s@estu.com");
		supp2.setContact("James Brown");
		supplierService.add(supp2);
	}
	
	private void loadUser() {
		User calvin = new User("calvin", passwordEncoder.encode("123123"));
		calvin.setFirstname("Calvin");
		calvin.setLastname("Chirwa");
		calvin.setCreated(LocalDateTime.now());
		calvin.setEmail("cschirwa@gmail.com");
		calvin.setLocation("Johannesburg");
		calvin.setPhone("+27786101500");
		calvin.setPhone2("+27786101501");
		calvin.setActive(true);
		userRepository.save(calvin);
	}

}
