package view.recycler.card.com.recyclercardview.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import view.recycler.card.com.recyclercardview.Adapters.MyAdapter;
import view.recycler.card.com.recyclercardview.Models.Movie;
import view.recycler.card.com.recyclercardview.R;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //List layout
        mLayoutManager = new LinearLayoutManager(this);
        //Grid layout
        //mLayoutManager = new GridLayoutManager(this,2);
        //Grid layout with random sizes
        //iF you uses this layout you should disable "setHasFixedSize"
        //mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                deleteMovie(position);
            }
        });

        //Ayuda a mantener el tamaño del  recycler
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_menu:
                this.addMovie(0);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ArrayList<Movie> getAllMovies(){
        return new ArrayList<Movie>() {{
            add(new Movie("Forrest Gump", R.drawable.forrest_gump));
            add(new Movie("Hombre en llamas", R.drawable.hombre_en_llamas));
            add(new Movie("Mi nombre es Khan", R.drawable.khan));
            add(new Movie("La pasión de Cristo", R.drawable.pasion_cristo));
        }};
    }

    private void addMovie(int position){
        movies.add(position, new Movie("New movie", R.drawable.poster));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

}
