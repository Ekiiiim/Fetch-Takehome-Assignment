package fetch.takehome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Jackson mapper used for deserializing JSON
    private final ObjectMapper objectMapper =
            new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    List<ItemModel> itemList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get JSON from URL
        URL home = null;
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        try {
            home = new URL("https://fetch-hiring.s3.amazonaws.com");
            URL url = new URL(home + "/hiring.json");
            // Deserialize JSON into item list
            itemList = objectMapper.readValue(url, new TypeReference<List<ItemModel>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Filter items without name
        List<ItemModel> noNameItems = new ArrayList<>();
        for (ItemModel item : itemList) {
            if (item.getName() == null || item.getName().isEmpty()) {
                noNameItems.add(item);
            }
        }
        itemList.removeAll(noNameItems);

        // Sort the list
        Collections.sort(itemList, new Comparator<ItemModel>() {
            @Override
            public int compare(ItemModel o1, ItemModel o2) {
                if (o1.getListId() < o2.getListId()) {
                    return -1;
                } else if (o1.getListId() > o2.getListId()) {
                    return 1;
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        });

        // Setup adapter for recyclerview
        ListAdapter adapter = new ListAdapter(itemList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}