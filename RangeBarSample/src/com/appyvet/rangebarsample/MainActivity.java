
package com.appyvet.rangebarsample;

import com.appyvet.rangebar.RangeBar;
import com.appyvet.rangebarsample.colorpicker.ColorPickerDialog;
import com.appyvet.rangebarsample.colorpicker.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements MainContract.View,
        ColorPickerDialog.OnColorSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private MainContract.UserActionsListener mActionsListener;

    // Initializes the RangeBar in the application
    @Bind(R.id.rangebar1) RangeBar rangebar;
    @OnClick(R.id.enableRange)
    void onEnableRangeClick(View view) {
        rangebar.setRangeBarEnabled(!rangebar.isRangeBar());
    }
    @OnClick(R.id.disable)
    void onDisableRangeClick(View view) {
        rangebar.setEnabled(!rangebar.isEnabled());
    }

    @Bind(R.id.barColor) TextView barColorText;
    @OnClick(R.id.barColor)
    void onBarColorClick(View view) {
        mActionsListener.chooseBarColor();
    }

    @Bind(R.id.connectingLineColor) TextView connectingLineColorText;
    @OnClick(R.id.connectingLineColor)
    void onConnectingLineColorClick(View view) {
        mActionsListener.chooseConnectingLineColor();
    }
    @Bind(R.id.pinColor) TextView pinColorText;
    @OnClick(R.id.pinColor)
    void onPinColorClick(View view) {
        mActionsListener.choosePinColor();
    }

    @Bind(R.id.textColor) TextView textColorText;
    @OnClick(R.id.textColor)
    void onTextColorClick(View view) {
        mActionsListener.chooseTextColor();
    }

    @Bind(R.id.tickColor) TextView tickColorText;
    @OnClick(R.id.tickColor)
    void onTickColorTextClick(View view) {
        mActionsListener.chooseTickColor();
    }

    @Bind(R.id.selectorColor) TextView selectorColorText;
    @OnClick(R.id.selectorColor)
    void onSelectorColorTextClick(View view) {
        mActionsListener.chooseSelectorColor();
    }

    @Bind(R.id.leftIndexValue) EditText leftIndexValue;
    @Bind(R.id.rightIndexValue) EditText rightIndexValue;
    @Bind(R.id.tickStart) TextView tickStart;
    @Bind(R.id.tickStartSeek) SeekBar tickStartSeek;

    @OnClick(R.id.setIndex)
    void onIndexClick(View view) {
        // Gets the String values of all the texts
        String leftIndex = leftIndexValue.getText().toString();
        String rightIndex = rightIndexValue.getText().toString();

        // Catches any IllegalArgumentExceptions; if fails, should throw
        // a dialog warning the user
        try {
            if (!leftIndex.isEmpty() && !rightIndex.isEmpty()) {
                int leftIntIndex = Integer.parseInt(leftIndex);
                int rightIntIndex = Integer.parseInt(rightIndex);
                rangebar.setRangePinsByIndices(leftIntIndex, rightIntIndex);
            }
        } catch (IllegalArgumentException e) {
        }
    }
    @OnClick(R.id.setValue)
    void onSetValueClick(View view) {
        // Gets the String values of all the texts
        String leftValue = leftIndexValue.getText().toString();
        String rightValue = rightIndexValue.getText().toString();

        // Catches any IllegalArgumentExceptions; if fails, should throw
        // a dialog warning the user
        try {
            if (!leftValue.isEmpty() && !rightValue.isEmpty()) {
                float leftIntIndex = Float.parseFloat(leftValue);
                float rightIntIndex = Float.parseFloat(rightValue);
                rangebar.setRangePinsByValue(leftIntIndex, rightIntIndex);
            }
        } catch (IllegalArgumentException e) {
        }
    }

    @Bind(R.id.tickEnd) TextView tickEnd;
    @Bind(R.id.tickEndSeek) SeekBar tickEndSeek;

    @Bind(R.id.tickInterval) TextView tickInterval;
    @Bind(R.id.tickIntervalSeek) SeekBar tickIntervalSeek;

    @Bind(R.id.barWeight) TextView barWeight;
    @Bind(R.id.barWeightSeek) SeekBar barWeightSeek;

    @Bind(R.id.connectingLineWeight) TextView connectingLineWeight;
    @Bind(R.id.connectingLineWeightSeek) SeekBar connectingLineWeightSeek;

    @Bind(R.id.thumbRadius) TextView thumbRadius;
    @Bind(R.id.thumbRadiusSeek) SeekBar thumbRadiusSeek;

    // Saves the state upon rotating the screen/restarting the activity
    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        mActionsListener.saveState(bundle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mActionsListener.restoreState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes title bar and sets content view
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Sets fonts for all
//        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
////        ViewGroup root = (ViewGroup) findViewById(R.id.mylayout);
////        setFont(root, font);

        //Sets the buttons to bold.
//        barColor.setTypeface(font, Typeface.BOLD);
//        connectingLineColor.setTypeface(font, Typeface.BOLD);
//        pinColor.setTypeface(font, Typeface.BOLD);

        // Sets the display values of the indices
        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                    int rightPinIndex,
                    String leftPinValue, String rightPinValue) {
                leftIndexValue.setText("" + leftPinIndex);
                rightIndexValue.setText("" + rightPinIndex);
            }

        });

        // Sets tickStart
        tickStartSeek.setOnSeekBarChangeListener(new OnBaseSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickStart(progress);
                } catch (IllegalArgumentException e) {
                }
                tickStart.setText("tickStart = " + progress);
            }
        });

        // Sets tickEnd
        tickEndSeek.setOnSeekBarChangeListener(new OnBaseSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickEnd(progress);
                } catch (IllegalArgumentException e) {
                }
                tickEnd.setText("tickEnd = " + progress);
            }
        });

        // Sets tickInterval
        tickIntervalSeek.setOnSeekBarChangeListener(new OnBaseSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickInterval(progress / 10.0f);
                } catch (IllegalArgumentException e) {
                }
                tickInterval.setText("tickInterval = " + progress / 10.0f);
            }
        });

        // Sets barWeight
        barWeightSeek.setOnSeekBarChangeListener(new OnBaseSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar barWeightSeek, int progress, boolean fromUser) {
                rangebar.setBarWeight(progress);
                barWeight.setText("barWeight = " + progress);
            }
        });

        // Sets connectingLineWeight
        connectingLineWeightSeek.setOnSeekBarChangeListener(new OnBaseSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar connectingLineWeightSeek, int progress,
                    boolean fromUser) {
                rangebar.setConnectingLineWeight(progress);
                connectingLineWeight.setText("connectingLineWeight = " + progress);
            }
        });

        // Sets thumbRadius
        thumbRadiusSeek.setOnSeekBarChangeListener(new OnBaseSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar thumbRadiusSeek, int progress, boolean fromUser){
                    rangebar.setPinRadius(progress);
                    thumbRadius.setText("Pin Radius = " + progress);
            }
        });

        mActionsListener = new MainPresenter(this);
    }

    /**
     * Sets the changed color using the ColorPickerDialog.
     *
     * @param component Component specifying which input is being used
     * @param newColor  Integer specifying the new color to be selected.
     */
    @Override
    public void onColorSelected(int newColor, Component component) {
        Log.d("Color selected"," new color = "+ newColor + ",compoment = "+ component);
        mActionsListener.updateComponentColor(newColor, component);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Initiates the colorPicker from within a button function.
     *
     * @param component    Component specifying which input is being used
     * @param initialColor Integer specifying the initial color choice. *
     * @param defaultColor Integer specifying the default color choice.
     */
    @Override
    public void initColorPicker(Component component, int initialColor, int defaultColor) {
        ColorPickerDialog colorPicker = ColorPickerDialog
                .newInstance(R.string.colorPickerTitle, Utils.ColorUtils.colorChoice(this),
                        initialColor, 4, ColorPickerDialog.SIZE_SMALL, component);
        colorPicker.setOnColorSelectedListener(this);
        colorPicker.show(getFragmentManager(), "color");
    }

    @Override
    public void showUnknownComponentColor(int newColor, Component component) {
        Log.d(TAG, "showUnknownComponentColor new color = "+ newColor +
                ",compoment = "+ component);
    }

    @Override
    public void updateBarColor(int newColor) {
        rangebar.setBarColor(newColor);
    }

    private static void updateTextColor(TextView textView, int color, String prefix) {
        String text = String.format("%s = #%06X", prefix, (0xFFFFFF & color));
        textView.setText(text);
        textView.setTextColor(color);
    }

    @Override
    public void updateBarColorText(int newColor) {
        updateTextColor(barColorText, newColor, "barColor");
        barColorText.setTextColor(newColor);
    }

    @Override
    public void updatePinTextColor(int newColor) {
        rangebar.setPinTextColor(newColor);
    }

    @Override
    public void updatePinTextColorText(int newColor) {
        updateTextColor(textColorText, newColor, "textColor");
        textColorText.setTextColor(newColor);
    }

    @Override
    public void updateConnectingLineColor(int newColor) {
        rangebar.setConnectingLineColor(newColor);
    }

    @Override
    public void updateConnectingLineColorText(int newColor) {
        updateTextColor(connectingLineColorText, newColor, "connectingLineColor");
        connectingLineColorText.setTextColor(newColor);
    }

    @Override
    public void updatePinColor(int newColor) {
        rangebar.setPinColor(newColor);
    }

    @Override
    public void updatePinColorText(int newColor) {
        updateTextColor(pinColorText, newColor, "pinColor");
        pinColorText.setTextColor(newColor);
    }

    @Override
    public void updateTickColor(int newColor) {
        rangebar.setTickColor(newColor);
    }

    @Override
    public void updateTickColorText(int newColor) {
        updateTextColor(tickColorText, newColor, "tickColor");
        tickColorText.setTextColor(newColor);
    }

    @Override
    public void updateSelectorColor(int newColor) {
        rangebar.setSelectorColor(newColor);
    }

    @Override
    public void updateSelectorColorText(int newColor) {
        updateTextColor(selectorColorText, newColor, "selectorColor");
        selectorColorText.setTextColor(newColor);
    }
}
