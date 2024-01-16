package br.com.stoom.store.brand;

import br.com.stoom.store.core.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BrandWriteTest {

    @Mock
    private BrandRepository productRepository;

    @InjectMocks
    private BrandWrite productWrite;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateStatus() throws Exception {
        Long productId = 1L;
        Status status = Status.ACTIVE;
        Brand product = new Brand().withId(productId);
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Brand updatedBrand = productWrite.updateStatus(productId, status);

        assertEquals(status, updatedBrand.getStatus());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateStatusWhenBrandNotFound() {
        Long productId = 1L;
        Status status = Status.ACTIVE;
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> productWrite.updateStatus(productId, status));
        assertEquals("Brand not found", exception.getMessage());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).save(any());
    }

}
