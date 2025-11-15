import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private int currentWeight = 0;
    ArrayList<T> box = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ArrayList<T> getBox() {
        return box;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public void addParcel(T parcel) {
        if (!hasFreeSpace(parcel.getWeight())) {
            System.out.println("В коробке недостаточно места для этой посылки");
            return;
        }
        box.add(parcel);
        setCurrentWeight(getCurrentWeight() + parcel.getWeight());

    }

    public void getAllParcels() {
        for (T parcel : box) {
            System.out.println(parcel);
        }
    }

    //внутренняя функция для проверки наличия места в коробке
    private boolean hasFreeSpace(int parcelWeight) {
        if (parcelWeight + currentWeight > maxWeight) {
            return false;
        }
        return true;
    }
}
