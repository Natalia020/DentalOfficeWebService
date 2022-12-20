package com.example.dentistmysql.test;

import com.example.dentistmysql.controller.DentistController;
import com.example.dentistmysql.model.Store;
import com.example.dentistmysql.service.StoreService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DentistControllerTest {
    @Mock
    StoreService storeService;
    @InjectMocks
    private DentistController dentistController;
    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void submitNewProductTest() {

        Long id = 32L;
        String storeType = "Nowe wiertła";
        int Quantity = 17;

        Store expectedProduct = new Store(id, storeType, Quantity, null);
        when(storeService.createStore(storeType, Quantity)).thenReturn(expectedProduct);

        // Act
        String result = dentistController.createStore(storeType, Quantity);
        System.out.println(expectedProduct);

        // Assert
        assertEquals("redirect:/store-success", result);
    }
}
