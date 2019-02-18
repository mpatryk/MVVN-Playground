package l.mr.mvvnsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import l.mr.mvvnsample.R;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "l.mr.mvvnsample.EXTRA_ID";
    public static final String EXTRA_TITLE = "l.mr.mvvnsample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "l.mr.mvvnsample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "l.mr.mvvnsample.EXTRA_PRIORITY";

    private TextInputEditText editTextTitle;
    private TextInputEditText editTextDescription;
    private AppCompatSeekBar seekBarPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.activity_add_note_editTextTitle);
        editTextDescription = findViewById(R.id.activity_add_note_editTextDescription);
        seekBarPriority = findViewById(R.id.activity_add_note_seekBarPriority);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            seekBarPriority.setProgress(intent.getIntExtra(EXTRA_PRIORITY, 0));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = Objects.requireNonNull(editTextTitle.getText()).toString();
        String description = Objects.requireNonNull(editTextDescription.getText()).toString();
        int priority = seekBarPriority.getProgress();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
