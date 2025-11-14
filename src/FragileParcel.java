public class FragileParcel extends Parcel {

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    void packageItem() {
        System.out.println("Посылка "+ getDescription() + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    int getPrice() {
        return FragileDeliveryPrice;
    }
}
