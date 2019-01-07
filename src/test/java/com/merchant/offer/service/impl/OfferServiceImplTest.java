package com.merchant.offer.service.impl;

import static org.junit.Assert.*;

import com.merchant.offer.domain.Offer;
import com.merchant.offer.domain.Product;
import com.merchant.offer.exception.FieldValidationException;
import com.merchant.offer.exception.InvalidDateException;
import com.merchant.offer.exception.ResourceNotFoundException;
import com.merchant.offer.repository.OfferRepository;
import com.merchant.offer.repository.ProductRepository;
import com.merchant.offer.service.OfferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OfferServiceImpl.class)
public class OfferServiceImplTest {

    @MockBean
    private OfferRepository offerRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private OfferService offerService;

    Product p = new Product("Box of large tissues", "GBP", 89);

    Offer testValidOffer = new Offer(
            p,
            "20% off Coffee",
            "GBP",
            100,
            LocalDate.of(2019,1, 1),
            LocalDate.of(2019,1, 31)
    );

    @Test
    public void listAllTest() throws Exception {
        Mockito.when(offerRepository.findAll()).thenReturn(Arrays.asList(testValidOffer));
        assertEquals(offerService.listAll(), Arrays.asList(testValidOffer));
    }

    @Test
    public void findByProductIdTest() throws Exception {
        Mockito.when(offerRepository.findByProductId(1)).thenReturn(Arrays.asList(testValidOffer));
        assertEquals(offerService.findByProductId(1), Arrays.asList(testValidOffer));
    }

    @Test
    public void getByIdTest() throws Exception {
        Mockito.when(offerRepository.findById((long)1)).thenReturn(Optional.of(testValidOffer));
        assertEquals(offerService.getById((long)1), testValidOffer);
    }

    @Test
    public void createNewValidDateTest() throws Exception {
        Mockito.when(productRepository.findById((long)1)).thenReturn(Optional.of(p));
        offerService.createNew(
            1,
            "15% off cups",
            "GBP",
            100,
            LocalDate.of(2019, 01,1),
            LocalDate.of(2099, 01, 31)
        );
    }

    @Test(expected = InvalidDateException.class)
    public void createNewInvalidDateTest() throws Exception {
        Mockito.when(productRepository.findById((long)1)).thenReturn(Optional.of(p));
        offerService.createNew(
                1,
                "15% off cups",
                "GBP",
                100,
                LocalDate.of(2018, 01,01),
                LocalDate.of(2018, 01, 31)
        );
    }

    @Test(expected = FieldValidationException.class)
    public void createNewInvalidPriceTest() throws Exception {
        Mockito.when(productRepository.findById((long)1)).thenReturn(Optional.of(p));
        offerService.createNew(
                1,
                "15% off cups",
                "GBP",
                -100,
                LocalDate.of(2018, 01,01),
                LocalDate.of(2028, 01, 31)
        );
    }

    @Test(expected = FieldValidationException.class)
    public void createNewInvalidCurrencyTest() throws Exception {
        Mockito.when(productRepository.findById((long)1)).thenReturn(Optional.of(p));
        offerService.createNew(
                1,
                "15% off cups",
                "1GBP",
                -100,
                LocalDate.of(2018, 01,01),
                LocalDate.of(2028, 01, 31)
        );
    }

    @Test
    public void cancelOfferSuccessTest() throws Exception {
        Mockito.when(offerRepository.findById((long)1)).thenReturn(Optional.of(testValidOffer));
        offerService.cancelOffer(1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void cancelOfferFailureTest() throws Exception {
        Mockito.when(offerRepository.findById((long)2)).thenReturn(Optional.empty());
        offerService.cancelOffer(2);
    }
}
