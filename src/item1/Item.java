package item1;

public class Item implements IItem {

    public static Item create() {
        return new Item();
    }

    public static IItem createInterface() {
        return new Item();
    }
}
