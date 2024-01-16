package br.com.stoom.store.product;

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

class ProductWriteTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductWrite productWrite;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() throws Exception {
        Product product = new Product();
        when(productRepository.existsByName(product.getName())).thenReturn(false);
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productWrite.create(product);

        verify(productRepository, times(1)).existsByName(product.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testCreateProductWhenProductExists() throws Exception {
        Product product = new Product();
        when(productRepository.existsByName(product.getName())).thenReturn(true);

        Exception exception = assertThrows(Exception.class, () -> productWrite.create(product));
        assertEquals("Product exists", exception.getMessage());

        verify(productRepository, times(1)).existsByName(product.getName());
        verify(productRepository, never()).save(product);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        Product product = new Product().withId(productId);
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productWrite.update(productId, product);

        assertEquals(productId, updatedProduct.getId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateStatus() throws Exception {
        Long productId = 1L;
        Status status = Status.ACTIVE;
        Product product = new Product().withId(productId);
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productWrite.updateStatus(productId, status);

        assertEquals(status, updatedProduct.getStatus());
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateStatusWhenProductNotFound() {
        Long productId = 1L;
        Status status = Status.ACTIVE;
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> productWrite.updateStatus(productId, status));
        assertEquals("Product not found", exception.getMessage());

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).save(any());
    }

    @Test
    void testDeleteProductById() {
        Long productId = 1L;

        productWrite.deleteById(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}
