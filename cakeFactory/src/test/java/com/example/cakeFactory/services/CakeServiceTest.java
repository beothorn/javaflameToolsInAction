package com.example.cakeFactory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cakeFactory.NationalCakeRegistryService;
import com.example.cakeFactory.entities.Cake;
import com.example.cakeFactory.entities.Inventory;
import com.example.cakeFactory.entities.Sale;
import com.example.cakeFactory.repos.CakeRepository;
import com.example.cakeFactory.repos.InventoryRepository;
import com.example.cakeFactory.repos.SaleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private NationalCakeRegistryService nationalCakeRegistryService;

    @Mock
    private Taxes taxes;

    @InjectMocks
    private CakeService cakeService;

    @Test
    void testSellCake_Success() {
        // Arrange
        long cakeId = 1L;

        Cake cake = new Cake();
        cake.setId(cakeId);
        cake.setPrice(10.0);

        when(cakeRepository.findById(cakeId)).thenReturn(Optional.of(cake));

        Inventory inventory = new Inventory();
        inventory.setCake(cake);
        inventory.setQuantityAvailable(5);

        when(inventoryRepository.findByCake(cake)).thenReturn(inventory);

        double finalPrice = 12.0; // 10% taxes
        when(taxes.priceWithTaxes(cake.getPrice())).thenReturn(finalPrice);

        // Act
        Sale sale = cakeService.sellCake(cakeId);

        // Assert
        assertNotNull(sale);
        assertEquals(cake, sale.getCake());
        assertEquals(cake.getPrice(), sale.getPrice());
        assertEquals(finalPrice, sale.getFinalPriceWithTaxes());

        assertEquals(4, inventory.getQuantityAvailable());
        verify(inventoryRepository).save(inventory);
        verify(saleRepository).save(sale);
        verify(nationalCakeRegistryService).registerCakeSale(cakeId);
    }

    @Test
    void testGetAllCakesListings() {
        // Arrange
        Cake cake1 = new Cake();
        cake1.setId(1L);
        cake1.setFlavour("Chocolate Cake");

        Cake cake2 = new Cake();
        cake2.setId(2L);
        cake2.setFlavour("Vanilla Cake");

        List<Cake> cakes = Arrays.asList(cake1, cake2);
        when(cakeRepository.findAll()).thenReturn(cakes);

        Inventory inventory1 = new Inventory();
        inventory1.setCake(cake1);
        inventory1.setQuantityAvailable(10);

        Inventory inventory2 = new Inventory();
        inventory2.setCake(cake2);
        inventory2.setQuantityAvailable(0);

        when(inventoryRepository.findByCake(cake1)).thenReturn(inventory1);
        when(inventoryRepository.findByCake(cake2)).thenReturn(inventory2);

        // Act
        List<CakeListing> cakeListings = cakeService.getAllCakesListings();

        // Assert
        assertNotNull(cakeListings);
        assertEquals(1, cakeListings.size());
        CakeListing listing = cakeListings.get(0);
        assertEquals(cake1, listing.getCake());
        assertEquals(10, listing.getQuantityAvailable());
    }

    @Test
    void testGetCakeById() {
        // Arrange
        Long cakeId = 1L;
        Cake cake = new Cake();
        cake.setId(cakeId);
        cake.setFlavour("Strawberry Cake");

        when(cakeRepository.findById(cakeId)).thenReturn(Optional.of(cake));

        // Act
        Cake result = cakeService.getCakeById(cakeId);

        // Assert
        assertNotNull(result);
        assertEquals(cake, result);
    }

    @Test
    void testGetSales() {
        // Arrange
        Sale sale1 = new Sale();
        sale1.setSaleId(1L);

        Sale sale2 = new Sale();
        sale2.setSaleId(2L);

        List<Sale> sales = Arrays.asList(sale1, sale2);
        when(saleRepository.findAll()).thenReturn(sales);

        // Act
        List<Sale> result = cakeService.getSales();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(sale1));
        assertTrue(result.contains(sale2));
    }

}