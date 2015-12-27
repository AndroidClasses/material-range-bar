/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appyvet.rangebarsample;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MainContract {

    interface View {
        void initColorPicker(Component component, int initialColor, int defaultColor);

        void showUnknownComponentColor(int newColor, Component component);

        void updateBarColor(int barColor);
        void updateBarColorText(int newColor);

        void updatePinTextColor(int newColor);
        void updatePinTextColorText(int newColor);

        void updateConnectingLineColor(int newColor);
        void updateConnectingLineColorText(int newColor);

        void updatePinColor(int newColor);
        void updatePinColorText(int newColor);

        void updateTickColor(int newColor);
        void updateTickColorText(int newColor);

        void updateSelectorColor(int newColor);
        void updateSelectorColorText(int newColor);
//        void setProgressIndicator(boolean active);
//        void showVideos(List<MyVishotModel> notes);
//        void showAddVideo();
//        void showVideoDetailUi(String videoId);
    }

    interface UserActionsListener {
        /// show color each picker
        void chooseBarColor();
        void chooseConnectingLineColor();
        void choosePinColor();
        void chooseTextColor();
        void chooseTickColor();
        void chooseSelectorColor();

        /// update component's new color
        void updateComponentColor(int newColor, Component component);

        // todo: should a presenter involve with the Bundle class?
        /// save and restore state with bundle
        void saveState(Bundle bundle);
        void restoreState(Bundle bundle);

//        void loadVideos(boolean forceUpdate);
//        void addNewVideo();
//        void openVideoDetails(@NonNull MyVishotModel requestedNote);
    }
}
