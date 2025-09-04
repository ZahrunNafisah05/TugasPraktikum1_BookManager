import org.example.Book;
import org.example.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setup() {
        bookManager = new BookManager();
    }

    @Test
    @DisplayName("Test menambahkan buku")
    void testAddBook() {
        Book buku = new Book("Pemrograman", "Andi", 2020);
        bookManager.addBook(buku);
        assertEquals(1, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test menghapus buku yang ada")
    void testRemoveExistingBook() {
        Book buku = new Book("Basis Data", "Erlangga", 2021);
        bookManager.addBook(buku);
        boolean removed = bookManager.removeBook("Basis Data");
        assertTrue(removed);
        assertEquals(0, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test menghapus buku yang tidak ada")
    void testRemoveNonExistingBook() {
        boolean removed = bookManager.removeBook("Basis Data");
        assertFalse(removed);
        assertEquals(0, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test mencari buku berdasarkan author")
    void testFindBooksByAuthor() {
        Book buku1 = new Book("Pemrograman", "Andi", 2020);
        Book buku2 = new Book("Jaringan", "Budi", 2019);
        Book buku3 = new Book("Basis Data", "Andi", 2021);
        bookManager.addBook(buku1);
        bookManager.addBook(buku2);
        bookManager.addBook(buku3);

        List<Book> foundBooks = bookManager.findBooksByAuthor("Andi");
        assertEquals(2, foundBooks.size());
        assertTrue(foundBooks.contains(buku1));
        assertTrue(foundBooks.contains(buku3));
    }

    @Test
    @DisplayName("Test mendapatkan semua buku")
    void testGetAllBooks() {
        Book buku1 = new Book("Pemrograman", "Andi", 2020);
        Book buku2 = new Book("Jaringan", "Budi", 2019);
        bookManager.addBook(buku1);
        bookManager.addBook(buku2);

        List<Book> allBooks = bookManager.getAllBooks();
        assertEquals(2, allBooks.size());
        assertTrue(allBooks.contains(buku1));
        assertTrue(allBooks.contains(buku2));
    }

    // 🔹 Tambahan test untuk findBooksByYear
    @Test
    @DisplayName("Test mencari buku berdasarkan tahun")
    void testFindBooksByYear() {
        Book buku1 = new Book("Pemrograman", "Andi", 2020);
        Book buku2 = new Book("Jaringan", "Budi", 2019);
        bookManager.addBook(buku1);
        bookManager.addBook(buku2);

        List<Book> foundBooks = bookManager.findBooksByYear(2020);
        assertEquals(1, foundBooks.size());
        assertTrue(foundBooks.contains(buku1));
    }

    // 🔹 Tambahan test untuk containsBook
    @Test
    @DisplayName("Test mengecek apakah buku ada di dalam list")
    void testContainsBook() {
        Book buku = new Book("Algoritma", "Cici", 2022);
        bookManager.addBook(buku);

        assertTrue(bookManager.containsBook("Algoritma"));
        assertFalse(bookManager.containsBook("Tidak Ada"));
    }

    // 🔹 Tambahan test untuk clearAllBooks
    @Test
    @DisplayName("Test menghapus semua buku dari list")
    void testClearAllBooks() {
        bookManager.addBook(new Book("Pemrograman", "Andi", 2020));
        bookManager.addBook(new Book("Jaringan", "Budi", 2019));

        bookManager.clearAllBooks();
        assertEquals(0, bookManager.getBookCount());
        assertTrue(bookManager.getAllBooks().isEmpty());
    }

    // 🔹 Test validasi exception Book constructor
    @Test
    @DisplayName("Test membuat buku dengan judul kosong harus gagal")
    void testBookInvalidTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book("", "Andi", 2020);
        });
    }

    @Test
    @DisplayName("Test membuat buku dengan author kosong harus gagal")
    void testBookInvalidAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book("Pemrograman", "", 2020);
        });
    }

    @Test
    @DisplayName("Test membuat buku dengan tahun di luar range harus gagal")
    void testBookInvalidYear() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Book("Pemrograman", "Andi", 1800);
        });
    }
}
