import com.chursinov.beautysalon.entity.product.Product;
import com.chursinov.beautysalon.repository.ProductRepository;
import com.chursinov.beautysalon.service.ProductService;
import com.chursinov.beautysalon.service.impl.ProductServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private List<Product> getProductsWithMasters(String... master) {
        List<Product> products = new ArrayList<>();
        for (String s : master) {
            Product product = new Product();
            product.setMaster(s);
            products.add(product);
        }
        return products;
    }

    @Test
    public void testGetAllProductsSortByMasters() {
        List<Product> products = getProductsWithMasters("kate", "ann", "bob");
        ProductRepository repository = mock(ProductRepository.class);
        when(repository.getAllProducts()).thenReturn(products);
        ProductService service = new ProductServiceImpl(repository);
        List<Product> actualList = service.getAllProductsOrderedBy("sortByMastersA-Z");
        List<Product> expectedList = getProductsWithMasters("ann", "bob", "kate");
        assertArrayEquals(expectedList.toArray(), actualList.toArray());
        verify(repository).getAllProducts();
    }


}
