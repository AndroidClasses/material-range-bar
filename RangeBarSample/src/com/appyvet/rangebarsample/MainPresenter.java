package com.appyvet.rangebarsample;

import android.os.Bundle;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yangfeng on 15-12-21.
 */
public class MainPresenter implements MainContract.UserActionsListener {
    // Sets the initial values such that the image will be drawn
    private static final int INDIGO_500 = 0xff3f51b5;

    // Sets variables to save the colors of each attribute
    private int mBarColor;
    private int mConnectingLineColor;
    private int mPinColor;
    private int mTextColor;
    private int mTickColor;
    private int mSelectorColor;


    //    private final VideosRepository mVideosRepository;
    private final MainContract.View mMainView;

    public MainPresenter(
            /*@NonNull VideosRepository videosRepository, */@NonNull MainContract.View videosView) {
//        this.mVideosRepository = videosRepository;
        this.mMainView = videosView;
    }

    @Override
    public void chooseBarColor() {
        mMainView.initColorPicker(Component.BAR_COLOR, mBarColor, mBarColor);

    }

    @Override
    public void updateComponentColor(int newColor, Component component) {
        switch (component) {
            case BAR_COLOR:
                mBarColor = newColor;
                mMainView.updateBarColor(newColor);
                mMainView.updateBarColorText(newColor);
                break;
            case TEXT_COLOR:
                mTextColor = newColor;
                mMainView.updatePinTextColor(newColor);
                mMainView.updatePinTextColorText(newColor);
                break;

            case CONNECTING_LINE_COLOR:
                mConnectingLineColor = newColor;
                mMainView.updateConnectingLineColor(newColor);
                mMainView.updateConnectingLineColorText(newColor);
                break;

            case PIN_COLOR:
                mPinColor = newColor;
                mMainView.updatePinColor(newColor);
                mMainView.updatePinColorText(newColor);
                break;
            case TICK_COLOR:
                mTickColor = newColor;
                mMainView.updateTickColor(newColor);
                mMainView.updateTickColorText(newColor);
                break;
            case SELECTOR_COLOR:
                mSelectorColor = newColor;
                mMainView.updateSelectorColor(newColor);
                mMainView.updateSelectorColorText(newColor);
                break;
            default:
                mMainView.showUnknownComponentColor(newColor, component);
                break;
        }
    }

    @Override
    public void saveState(Bundle bundle) {
        bundle.putInt("BAR_COLOR", mBarColor);
        bundle.putInt("CONNECTING_LINE_COLOR", mConnectingLineColor);
    }

    @Override
    public void restoreState(Bundle bundle) {
        mBarColor = bundle.getInt("BAR_COLOR");
        mConnectingLineColor = bundle.getInt("CONNECTING_LINE_COLOR");
    }

    @Override
    public void chooseConnectingLineColor() {
        mMainView.initColorPicker(Component.CONNECTING_LINE_COLOR, mConnectingLineColor,
                mConnectingLineColor);
    }

    @Override
    public void choosePinColor() {
        mMainView.initColorPicker(Component.PIN_COLOR, mPinColor, mPinColor);
    }

    @Override
    public void chooseTextColor() {
        mMainView.initColorPicker(Component.TEXT_COLOR, mTextColor, mTextColor);
    }

    @Override
    public void chooseTickColor() {
        mMainView.initColorPicker(Component.TICK_COLOR, mTickColor, mTickColor);
    }

    @Override
    public void chooseSelectorColor() {
        mMainView.initColorPicker(Component.SELECTOR_COLOR, mSelectorColor, mSelectorColor);
    }

//    @Override
//    public void loadVideos(boolean forceUpdate) {
//        mMainView.setProgressIndicator(true);
//        if (forceUpdate) {
//            mVideosRepository.refreshData();
//        }
//
//        // The network request might be handled in a different thread so make sure Espresso knows
//        // that the app is busy until the response is handled.
//        EspressoIdlingResource.increment(); // App is busy until further notice
//
//        mVideosRepository.getVideos(new VideosRepository.LoadVideosCallback() {
//            @Override
//            public void onVideosLoaded(List<MyVishotModel> videos) {
//                EspressoIdlingResource.decrement(); // Set app as idle.
//                mMainView.setProgressIndicator(false);
//                mMainView.showVideos(videos);
//            }
//        });
//    }

//    @Override
//    public void addNewVideo() {
//        mMainView.showAddVideo();
//    }

//    @Override
//    public void openVideoDetails(@NonNull MyVishotModel requestedNote) {
//        checkNotNull(requestedNote, "requestedNote cannot be null!");
//        mMainView.showVideoDetailUi(requestedNote.getId());
//    }
}
