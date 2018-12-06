package com.framgia.f_talk.util.rx;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
