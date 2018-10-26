package nz.org.cacophony.cacophonometer;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;

public class BatteryActivity extends AppCompatActivity implements IdlingResourceForEspressoTesting{
    private static final String TAG = BatteryActivity.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
    }

    @Override
    public void onResume() {
        super.onResume();
        Prefs prefs = new Prefs(getApplicationContext());

        boolean ignoreLowBattery = prefs.getIgnoreLowBattery();
        final Switch switchIgnoreLowBattery = findViewById(R.id.swIgnoreLowBattery);
        switchIgnoreLowBattery.setChecked(ignoreLowBattery);

    }

    void setLowBattery(){
        final Switch switchIgnoreLowBattery = findViewById(R.id.swIgnoreLowBattery);;
        boolean ignoreLowBattery = switchIgnoreLowBattery.isChecked();
        Prefs prefs = new Prefs(getApplicationContext());
        prefs.setIgnoreLowBattery(ignoreLowBattery);
    }



    public void next(@SuppressWarnings("UnusedParameters") View v) {
        try {
            setLowBattery();
            Intent intent = new Intent(this, FrequencyActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }
    }

    public void back(@SuppressWarnings("UnusedParameters") View v) {
        try {
            setLowBattery();
            Intent intent = new Intent(this, SoundActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }
    }

    @SuppressWarnings("SameReturnValue")
    public CountingIdlingResource getIdlingResource() {
        return registerIdlingResource;
    }

    @SuppressWarnings("SameReturnValue")
    public CountingIdlingResource getRecordNowIdlingResource() {
        return recordNowIdlingResource;
    }
}