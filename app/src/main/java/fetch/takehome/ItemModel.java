package fetch.takehome;

public class ItemModel {

    private int id;
    public int getId() { return id; }

    private int listId;
    public int getListId() { return listId; }

    private String name;
    public String getName() { return name; }

    public ItemModel(int newId, int newListId, String newName) {
        id = newId;
        listId = newListId;
        name = newName;
    }

    // Used by Jackson
    public ItemModel() { }
}
