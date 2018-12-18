package com.framgia.f_talk.screen.chat;

import android.support.v7.widget.LinearLayoutManager;

import com.framgia.f_talk.data.RepositoryManager;
import com.framgia.f_talk.util.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ChatActivityModule {
    @Provides
    ChatViewModel provideChatViewModel(RepositoryManager repositoryManager,
                                       SchedulerProvider schedulerProvider) {
        return new ChatViewModel(repositoryManager, schedulerProvider);
    }

    @Provides
    ChatAdapter provideChatAdapter() {
        return new ChatAdapter();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ChatActivity chatActivity) {
        return new LinearLayoutManager(chatActivity);
    }
}
