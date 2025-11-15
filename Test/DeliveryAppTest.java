import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DeliveryAppTest {
    private static StandardParcel sParcel;
    private static PerishableParcel pParcel;
    private static FragileParcel fParcel;
   @BeforeAll
   public static void  beforeEach() {
       sParcel = new StandardParcel("Носки", 50,"Проспект Лихачёва, 11", 1);
       fParcel = new FragileParcel("Фарфоровая кукушка", 50, "Проспект Лихачёва, 12", 1);
       pParcel = new PerishableParcel("Фаршмак", 50, "Проспект Лихачёва, 13", 1,3);
   }
    @Test
    public void isPerishableParcelExpired() {
        Assertions.assertTrue(pParcel.isExpired(5));
    }

    @Test
    public void priceForStandardShouldBe100withWeight50(){
        Assertions.assertEquals(100, sParcel.calculateDeliveryCost() );
    }

    @Test
    public void priceForFragileShouldBe200withWeight50(){
        Assertions.assertEquals(200, fParcel.calculateDeliveryCost());

    }

    @Test
    public void priceForPerishableShouldBe150withWeight50(){
        Assertions.assertEquals(150,pParcel.calculateDeliveryCost());

    }

    @Test
    public void shoudReturnPrice2ForStandardParcel() {
        Assertions.assertEquals(2,sParcel.getPrice());
    }
    @Test
    public void shoudReturnPrice4ForFragileParcel() {
        Assertions.assertEquals(4,fParcel.getPrice());

    }
    @Test
    public void shoudReturnPrice3ForPerishableParcel() {
        Assertions.assertEquals(3,pParcel.getPrice());
    }
    @Test
    public void shouldSuccessfullyAddParcelToBox() {
        ParcelBox<StandardParcel> standardBox = new ParcelBox<>(60);
        standardBox.addParcel(sParcel);
        Assertions.assertEquals(50, standardBox.getCurrentWeight());
        Assertions.assertEquals(1, standardBox.getBox().size());
        Assertions.assertEquals(sParcel,standardBox.getBox().get(0));
    }
    @Test
    public void shouldFailToAddParcelToBox() {
        ParcelBox<StandardParcel> standardBox = new ParcelBox<>(20);
        standardBox.addParcel(sParcel);
        Assertions.assertEquals(0, standardBox.getBox().size());
    }

    @Test
    public void shouldReturnExpiredParsel() {
       Assertions.assertTrue(pParcel.isExpired(10));
    }


}