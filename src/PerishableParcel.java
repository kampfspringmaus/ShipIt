public class PerishableParcel extends Parcel{
    int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    boolean isExpired(int currentDay) {
        if (getSendDay()+timeToLive>=currentDay) {
            return false;
        }
        return true;
    }

    int getPrice() {
        return PerishableDeliveryPrice;
    }

}
