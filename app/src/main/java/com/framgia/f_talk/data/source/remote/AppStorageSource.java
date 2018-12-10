package com.framgia.f_talk.data.source.remote;

import android.net.Uri;

import com.framgia.f_talk.data.source.remote.firebase.FirebaseCompletableTask;
import com.framgia.f_talk.data.source.remote.firebase.FirebaseTask;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class AppStorageSource implements StorageSource {
    @Inject
    public AppStorageSource() {
    }

    @Override
    public Maybe<byte[]> getBytes(StorageReference storageRef, long maxDownloadSizeBytes) {
        return Maybe.create(emitter -> FirebaseTask.assignOnTask(emitter,
                storageRef.getBytes(maxDownloadSizeBytes)));
    }

    @Override
    public Maybe<Uri> getDownloadUrl(StorageReference storageRef) {
        return Maybe.create(emitter -> FirebaseTask.assignOnTask(emitter,
                storageRef.getDownloadUrl()));
    }

    @Override
    public Single<FileDownloadTask.TaskSnapshot> getFile(StorageReference storageRef,
                                                         File destinationFile) {
        return Single.create(emitter -> {
            StorageTask<FileDownloadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.getFile(destinationFile).addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<FileDownloadTask.TaskSnapshot> getFile(StorageReference storageRef,
                                                         Uri destinationUri) {
        return Single.create(emitter -> {
            StorageTask<FileDownloadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.getFile(destinationUri).addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Maybe<StorageMetadata> getMetadata(StorageReference storageRef) {
        return Maybe.create(emitter -> FirebaseTask.assignOnTask(emitter,
                storageRef.getMetadata()));
    }

    @Override

    public Single<StreamDownloadTask.TaskSnapshot> getStream(StorageReference storageRef) {
        return Single.create(emitter -> {
            StorageTask<StreamDownloadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.getStream().addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<StreamDownloadTask.TaskSnapshot> getStream(StorageReference storageRef,
                                                             StreamDownloadTask.StreamProcessor
                                                                     processor) {
        return Single.create(emitter -> {
            StorageTask<StreamDownloadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.getStream(processor).addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putBytes(StorageReference storageRef, byte[] bytes) {
        return Single.create(emitter -> {
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.putBytes(bytes).addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });
            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putBytes(StorageReference storageRef, byte[] bytes,
                                                    StorageMetadata metadata) {
        return Single.create(emitter -> {
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.putBytes(bytes, metadata).addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri) {
        return Single.create(emitter -> {
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.putFile(uri).addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri,
                                                   StorageMetadata metadata) {
        return Single.create(emitter -> {
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.putFile(uri, metadata)
                            .addOnSuccessListener(emitter::onSuccess).addOnFailureListener(e -> {
                        if (!emitter.isDisposed())
                            emitter.onError(e);
                    });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri,
                                                   StorageMetadata metadata,
                                                   Uri existingUploadUri) {
        return Single.create(emitter -> {
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.putFile(uri, metadata, existingUploadUri)
                            .addOnSuccessListener(emitter::onSuccess).addOnFailureListener(e -> {
                        if (!emitter.isDisposed())
                            emitter.onError(e);
                    });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putStream(StorageReference storageRef,
                                                     InputStream stream,
                                                     StorageMetadata metadata) {
        return Single.create(emitter -> {
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.putStream(stream, metadata)
                            .addOnSuccessListener(emitter::onSuccess).addOnFailureListener(e -> {
                        if (!emitter.isDisposed())
                            emitter.onError(e);
                    });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putStream(StorageReference storageRef,
                                                     InputStream stream) {
        return Single.create(emitter -> {
            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask =
                    storageRef.putStream(stream).addOnSuccessListener(emitter::onSuccess)
                            .addOnFailureListener(e -> {
                                if (!emitter.isDisposed())
                                    emitter.onError(e);
                            });

            emitter.setCancellable(taskSnapshotStorageTask::cancel);
        });
    }

    @Override
    public Maybe<StorageMetadata> updateMetadata(StorageReference storageRef,
                                                 StorageMetadata metadata) {
        return Maybe.create(emitter -> FirebaseTask.assignOnTask(emitter,
                storageRef.updateMetadata(metadata)));
    }

    @Override
    public Completable delete(StorageReference storageRef) {
        return Completable.create(emitter -> FirebaseCompletableTask.assignOnTask(emitter,
                storageRef.delete()));
    }
}
