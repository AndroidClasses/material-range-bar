
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
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements
        ColorPickerDialog.OnColorSelectedListener {

    // Sets the initial values such that the image will be drawn
    private static final int INDIGO_500 = 0xff3f51b5;

    // Sets variables to save the colors of each attribute
    private int mBarColor;

    private int mConnectingLineColor;

    private int mPinColor;
    private int mTextColor;

    private int mTickColor;

    private int mSelectorColor;

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
        initColorPicker(Component.BAR_COLOR, mBarColor, mBarColor);
    }

    @Bind(R.id.connectingLineColor) TextView connectingLineColorText;
    @OnClick(R.id.connectingLineColor)
    void onConnectingLineColorClick(View view) {
        initColorPicker(Component.CONNECTING_LINE_COLOR, mConnectingLineColor,
                mConnectingLineColor);
    }
    @Bind(R.id.pinColor) TextView pinColorText;
    @OnClick(R.id.pinColor)
    void onPinColorClick(View view) {
        initColorPicker(Component.PIN_COLOR, mPinColor, mPinColor);
    }

    @Bind(R.id.textColor) TextView textColorText;
    @OnClick(R.id.textColor)
    void onTextColorClick(View view) {
        initColorPicker(Component.TEXT_COLOR, mTextColor, mTextColor);
    }

    @Bind(R.id.tickColor) TextView tickColorText;
    @OnClick(R.id.tickColor)
    void onTickColorTextClick(View view) {
        initColorPicker(Component.TICK_COLOR, mTickColor, mTickColor);
    }

    @Bind(R.id.selectorColor) TextView selectorColorText;
    @OnClick(R.id.selectorColor)
    void onSelectorColorTextClick(View view) {
        initColorPicker(Component.SELECTOR_COLOR, mSelectorColor, mSelectorColor);
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
        bundle.putInt("BAR_COLOR", mBarColor);
        bundle.putInt("CONNECTING_LINE_COLOR", mConnectingLineColor);
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
        tickStartSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickStart(progress);
                } catch (IllegalArgumentException e) {
                }
                tickStart.setText("tickStart = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets tickEnd
        tickEndSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickEnd(progress);
                } catch (IllegalArgumentException e) {
                }
                tickEnd.setText("tickEnd = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets tickInterval
        tickIntervalSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar tickCountSeek, int progress, boolean fromUser) {
                try {
                    rangebar.setTickInterval(progress / 10.0f);
                } catch (IllegalArgumentException e) {
                }
                tickInterval.setText("tickInterval = " + progress / 10.0f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets barWeight
        barWeightSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar barWeightSeek, int progress, boolean fromUser) {
                rangebar.setBarWeight(progress);
                barWeight.setText("barWeight = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets connectingLineWeight
        connectingLineWeightSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar connectingLineWeightSeek, int progress,
                    boolean fromUser) {
                rangebar.setConnectingLineWeight(progress);
                connectingLineWeight.setText("connectingLineWeight = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Sets thumbRadius
        thumbRadiusSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar thumbRadiusSeek, int progress, boolean fromUser){
                    rangebar.setPinRadius(progress);
                    thumbRadius.setText("Pin Radius = " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /**
     * Sets the changed color using the ColorPickerDialog.
     *
     * @param component Component specifying which input is being used
     * @param newColor  Integer specifying the new color to be selected.
     */
    @Override
    public void onColorSelected(int newColor, Component component) {
        Log.d("Color selected"," new color = "+newColor+ ",compoment = "+component);
        String hexColor = String.format("#%06X", (0xFFFFFF & newColor));

        switch (component) {
            case BAR_COLOR:
                mBarColor = newColor;
                rangebar.setBarColor(newColor);
                barColorText.setText("barColor = " + hexColor);
                barColorText.setTextColor(newColor);
                break;
            case TEXT_COLOR:
                mTextColor = newColor;
                rangebar.setPinTextColor(newColor);
//                final TextView textColorText = (TextView) findViewById(R.id.textColor);
                textColorText.setText("textColor = " + hexColor);
                textColorText.setTextColor(newColor);
                break;

            case CONNECTING_LINE_COLOR:
                mConnectingLineColor = newColor;
                rangebar.setConnectingLineColor(newColor);
                connectingLineColorText.setText("connectingLineColor = " + hexColor);
                connectingLineColorText.setTextColor(newColor);
                break;

            case PIN_COLOR:
                mPinColor = newColor;
                rangebar.setPinColor(newColor);
//                final TextView pinColorText = (TextView) findViewById(R.id.pinColor);
                pinColorText.setText("pinColor = " + hexColor);
                pinColorText.setTextColor(newColor);
                break;
            case TICK_COLOR:
                mTickColor = newColor;
                rangebar.setTickColor(newColor);
//                final TextView tickColorText = (TextView) findViewById(R.id.tickColor);
                tickColorText.setText("tickColor = " + hexColor);
                tickColorText.setTextColor(newColor);
                break;
            case SELECTOR_COLOR:
                mSelectorColor = newColor;
                rangebar.setSelectorColor(newColor);
//                final TextView selectorColorText = (TextView) findViewById(R.id.selectorColor);
                selectorColorText.setText("selectorColor = " + hexColor);
                selectorColorText.setTextColor(newColor);
                break;
        }
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
    private void initColorPicker(Component component, int initialColor, int defaultColor) {
        ColorPickerDialog colorPicker = ColorPickerDialog
                .newInstance(R.string.colorPickerTitle, Utils.ColorUtils.colorChoice(this),
                        initialColor, 4, ColorPickerDialog.SIZE_SMALL, component);
        colorPicker.setOnColorSelectedListener(this);
        colorPicker.show(getFragmentManager(), "color");
    }
}
