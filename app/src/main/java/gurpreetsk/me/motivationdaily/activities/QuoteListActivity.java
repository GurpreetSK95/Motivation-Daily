package gurpreetsk.me.motivationdaily.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.fragments.QuoteFragment;
import gurpreetsk.me.motivationdaily.fragments.QuoteListFragment;
import gurpreetsk.me.motivationdaily.utils.AuthorImageUrl;
import gurpreetsk.me.motivationdaily.utils.Constants;

public class QuoteListActivity extends AppCompatActivity implements QuoteListFragment.Callback {

    //    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    //    @BindView(R.id.detail_image_view)
    ImageView authorImage;
    LinearLayout twoPaneView;

    public static boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        ButterKnife.bind(this);

        //TODO:NPE because of this
//        if(getSupportActionBar()!=null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTwoPane = findViewById(R.id.two_pane_view) != null;
        String authorName = getIntent().getStringExtra(Constants.AUTHOR_NAME_KEY);

        if (!mTwoPane) {
            collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
            authorImage = (ImageView) findViewById(R.id.detail_image_view);


            collapsingToolbarLayout.setTitle(authorName);
//        collapsingToolbarLayout.setcolor(getIntent().getIntExtra(Constants.MUTED_COLOR, getResources().getColor(R.color.colorPrimary)));
            Glide.with(this)
                    .load(AuthorImageUrl.getAuthorImage(authorName))
                    .into(authorImage);
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(Constants.QUOTES_KEY, getIntent().getStringArrayListExtra(Constants.QUOTES_KEY));
        bundle.putString(Constants.AUTHOR_NAME_KEY, authorName);
        QuoteListFragment quoteListFragment = new QuoteListFragment();
        quoteListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.quote_list_container, quoteListFragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quote_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!mTwoPane)
            supportFinishAfterTransition();
    }

    @Override
    public void OnItemSelected(ArrayList<String> quotes, String authorName, int position) {

        if (mTwoPane) {



        } else {

            Intent intent = new Intent(this, QuoteViewActivity.class);
            intent.putStringArrayListExtra(Constants.QUOTES_KEY, quotes);
            intent.putExtra(Constants.QUOTE_NUMBER_KEY, quotes.get(position));
            startActivity(intent);

        }
    }
}
