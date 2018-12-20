package com.framgia.f_talk.screen.chat;

public interface ChatNavigator {
    void getMessage();

    void chooseImage();

    void onSendMessageSuccess();

    void onSendMessageError();

    void onBack();

    void scrollTo(int index);
}

