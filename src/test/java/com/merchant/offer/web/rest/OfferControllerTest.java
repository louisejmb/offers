package com.merchant.offer.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import com.merchant.offer.domain.Offer;
import com.merchant.offer.domain.Product;
import com.merchant.offer.service.OfferService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OfferController.class})
@WebMvcTest(OfferController.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfferService offerService;

    private OfferController offerController;

    String exampleListOfferJson = "[{\n" +
            "    \"id\": 0,\n" +
            "    \"product\": {\n" +
            "        \"id\": 0,\n" +
            "        \"name\": \"Jelly\",\n" +
            "        \"currency\": \"GBP\",\n" +
            "        \"price\": 99\n" +
            "    },\n" +
            "    \"description\": \"Half price Jelly.\",\n" +
            "    \"currency\": \"GBP\",\n" +
            "    \"price\": 100,\n" +
            "    \"startTime\": \"2019-01-01\",\n" +
            "    \"endTime\": \"2019-01-31\",\n" +
            "    \"status\": \"ACTIVE\"\n" +
            "}]";

    String exampleOfferJson = "{\n" +
            "    \"id\": 0,\n" +
            "    \"product\": {\n" +
            "        \"id\": 0,\n" +
            "        \"name\": \"Jelly\",\n" +
            "        \"currency\": \"GBP\",\n" +
            "        \"price\": 99\n" +
            "    },\n" +
            "    \"description\": \"Half price Jelly.\",\n" +
            "    \"currency\": \"GBP\",\n" +
            "    \"price\": 100,\n" +
            "    \"startTime\": \"2019-01-01\",\n" +
            "    \"endTime\": \"2019-01-31\",\n" +
            "    \"status\": \"ACTIVE\"\n" +
            "}";

    Product testProduct = new Product("Jelly", "GBP", 99);

    Offer testOffer = new Offer(
            testProduct,
            "Half price Jelly.",
            "GBP",
            100,
            LocalDate.of(2019,1, 1),
            LocalDate.of(2019,1, 31)
    );

    @Test
    public void listAllOffersTest() throws Exception {
        given(this.offerService.listAll()).willReturn(Arrays.asList(testOffer));
        mockMvc.perform(get("/offer")).andExpect(status().isOk()).andExpect(content().json(exampleListOfferJson));
    }

    @Test
    public void getOfferByIdTest() throws Exception {
        given(this.offerService.getById(new Long(1))).willReturn(testOffer);
        mockMvc.perform(get("/offer/1")).andExpect(status().isOk()).andExpect(content().json(exampleOfferJson));
    }

    @Test
    public void getOfferByProductIdTest() throws Exception {
        given(this.offerService.findByProductId(new Long(1))).willReturn(Arrays.asList(testOffer));
        mockMvc.perform(get("/product/1/offers")).andExpect(status().isOk()).andExpect(content().json(exampleListOfferJson));
    }

    @Test
    public void addOfferTest() throws Exception {
        mockMvc.perform(post("/offer")
                .param("description", "Carrot")
                .param("currency", "GBP")
                .param("price", "100")
                .param("productId", "1")
                .param("startTime", "2019-01-01")
                .param("endTime", "2019-01-31"))
                .andExpect(status().isOk());
    }

    @Test
    public void cancelOfferTest() throws Exception {
        mockMvc.perform(delete("/offer/1")).andExpect(status().isOk());
    }
}