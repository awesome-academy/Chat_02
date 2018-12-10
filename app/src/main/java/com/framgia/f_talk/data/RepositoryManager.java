package com.framgia.f_talk.data;

import com.framgia.f_talk.data.source.remote.AuthenticationSource;
import com.framgia.f_talk.data.source.remote.RealtimeDatabaseSource;
import com.framgia.f_talk.data.source.remote.StorageSource;

public interface RepositoryManager extends AuthenticationSource, RealtimeDatabaseSource,
        StorageSource {

}
