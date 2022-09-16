import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {

    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product product1 = new Book(1, "Евгений Онегин", 300, "А.С.Пушкин");
    Product product2 = new Book(222, "Преступление и наказание", 350, "Ф.М. Достоевский");
    Product product3 = new Smartphone(15, "Iphone 12", 50_000, "Apple");
    Product product4 = new Smartphone(333, "Xiaomi Redmi 10", 27_000, "Xiaomi");


    @Test
    public void shouldSearchByName() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);

        Product[] expected = {product1};
        Product[] actual = manager.searchBy("Евгений Онегин");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByName_2() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);

        Product[] expected = {product4};
        Product[] actual = manager.searchBy("Xiaomi Redmi 10");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchByWrongName() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Анна Каренина");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNoProductsAdded() {

        Product[] expected = {};
        Product[] actual = manager.searchBy("Iphone 12");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfOneProductAdded() {
        manager.add(product4);

        Product[] expected = {product4};
        Product[] actual = manager.searchBy("Xiaomi Redmi 10");

        Assertions.assertArrayEquals(expected, actual);
    }
}