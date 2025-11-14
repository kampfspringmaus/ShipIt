public class PerishableParcel extends Parcel{
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
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

    @Override
    public String toString() {
        return super.toString() + ", Срок годности: " + getTimeToLive();
    }

}
