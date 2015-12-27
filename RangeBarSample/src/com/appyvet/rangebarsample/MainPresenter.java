package com.appyvet.rangebarsample;

import android.support.annotation.NonNull;

import com.appyvet.rangebarsample.util.EspressoIdlingResource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by yangfeng on 15-12-21.
 */
public class MainPresenter implements MainContract.UserActionsListener {

//    private final VideosRepository mVideosRepository;
    private final MainContract.View mVideosView;

    public MainPresenter(
            /*@NonNull VideosRepository videosRepository, */@NonNull MainContract.View videosView) {
//        this.mVideosRepository = videosRepository;
        this.mVideosView = videosView;
    }

    @Override
    public void loadVideos(boolean forceUpdate) {
        mVideosView.setProgressIndicator(true);
        if (forceUpdate) {
//            mVideosRepository.refreshData();
        }

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

//        mVideosRepository.getVideos(new VideosRepository.LoadVideosCallback() {
//            @Override
//            public void onVideosLoaded(List<MyVishotModel> videos) {
//                EspressoIdlingResource.decrement(); // Set app as idle.
//                mVideosView.setProgressIndicator(false);
//                mVideosView.showVideos(videos);
//            }
//        });
    }

    @Override
    public void addNewVideo() {
        mVideosView.showAddVideo();
    }

//    @Override
//    public void openVideoDetails(@NonNull MyVishotModel requestedNote) {
//        checkNotNull(requestedNote, "requestedNote cannot be null!");
//        mVideosView.showVideoDetailUi(requestedNote.getId());
//    }
}
