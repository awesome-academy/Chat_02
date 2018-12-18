package com.framgia.f_talk.data;

import com.framgia.f_talk.data.source.remote.AuthenticationSource;
import com.framgia.f_talk.data.source.remote.RealtimeDatabaseSource;

public interface RepositoryManager extends AuthenticationSource, RealtimeDatabaseSource {

}
