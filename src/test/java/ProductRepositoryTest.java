import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {

    Product product1 = new Book(1, "Евгений Онегин", 300, "А.С.Пушкин");
    Product product2 = new Book(222, "Преступление и наказание", 350, "Ф.М. Достоевский");
    Product product3 = new Smartphone(15, "Iphone 12", 50_000, "Apple");
    Product product4 = new Smartphone(333, "Xiaomi Redmi 10", 27_000, "Xiaomi");

    @Test
    public void shouldSaveProducts() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);

        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSaveProduct() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);

        Product[] expected = {product1};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSaveProductIfIdIsAlreadyExist() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product2);
        });
    }

    @Test
    public void shouldRemoveProductsById() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);
        repo.removeById(product3.getId());

        Product[] expected = {product1, product2, product4};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveNotExistingProductsById() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.save(product4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(20);
        });
    }
}