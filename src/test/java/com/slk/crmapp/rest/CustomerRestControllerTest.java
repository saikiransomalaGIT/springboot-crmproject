package com.slk.crmapp.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.slk.crmapp.entity.Customer;
import com.slk.crmapp.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerRestController.class)
public class CustomerRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	Customer mockCustomer = new Customer(1, "Poornima", "Patel", "patel@slkgroup.com");

	String exampleCustomerJson = "{\r\n\"id\": 4,\r\n\"firstName\": \"Sai\",\r\n\"lastName\": \"Kiran\",\r\n\"email\": \"kiran@slkgroup.com\"\r\n}";
	
	@Test
	public void getCustomer() throws Exception {

		Mockito.when(customerService.getCustomer(Mockito.any())).thenReturn(mockCustomer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/customers/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,firstName:Poornima,lastName:Patel,email:patel@slkgroup.com}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void saveCustomer() throws Exception {
	//	Customer mockCustomer = new Customer(4, "Sai", "Kiran", "kiran@slkgroup.com");

		// Mockito.when(customerService.saveCustomer(Mockito.anyInt(),
		// Mockito.any())).thenReturn(mockCustomer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/customers")
				.accept(MediaType.APPLICATION_JSON).content(exampleCustomerJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals("application/json", response.getHeader(HttpHeaders.CONTENT_TYPE));

	}

}
