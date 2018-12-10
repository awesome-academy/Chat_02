package com.framgia.f_talk.data.source.remote;

import android.net.Uri;

import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface StorageSource {
    Maybe<byte[]> getBytes(StorageReference storageRef,
                           long maxDownloadSizeBytes);

    Maybe<Uri> getDownloadUrl(StorageReference storageRef);

    Single<FileDownloadTask.TaskSnapshot> getFile(StorageReference storageRef,
                                                  File destinationFile);

    Single<FileDownloadTask.TaskSnapshot> getFile(StorageReference storageRef, Uri destinationUri);

    Maybe<StorageMetadata> getMetadata(StorageReference storageRef);

    Single<StreamDownloadTask.TaskSnapshot> getStream(StorageReference storageRef);

    Single<StreamDownloadTask.TaskSnapshot> getStream(StorageReference storageRef,
                                                      StreamDownloadTask.StreamProcessor processor);

    Single<UploadTask.TaskSnapshot> putBytes(StorageReference storageRef, byte[] bytes);

    Single<UploadTask.TaskSnapshot> putBytes(StorageReference storageRef, byte[] bytes,
                                             StorageMetadata metadata);

    Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri);

    Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri,
                                            StorageMetadata metadata);

    Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri,
                                            StorageMetadata metadata, Uri existingUploadUri);

    Single<UploadTask.TaskSnapshot> putStream(StorageReference storageRef, InputStream stream,
                                              StorageMetadata metadata);

    Single<UploadTask.TaskSnapshot> putStream(StorageReference storageRef, InputStream stream);

    Maybe<StorageMetadata> updateMetadata(StorageReference storageRef, StorageMetadata metadata);

    Completable delete(StorageReference storageRef);
}
