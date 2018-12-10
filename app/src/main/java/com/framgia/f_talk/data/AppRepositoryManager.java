package com.framgia.f_talk.data;

import android.net.Uri;

import com.framgia.f_talk.data.source.remote.AuthenticationSource;
import com.framgia.f_talk.data.source.remote.RealtimeDatabaseSource;
import com.framgia.f_talk.data.source.remote.StorageSource;
import com.framgia.f_talk.data.source.remote.firebase.FirebaseChildEvent;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

@Singleton
public class AppRepositoryManager implements RepositoryManager {
    private final AuthenticationSource mAuthenticationSource;
    private final RealtimeDatabaseSource mRealtimeDatabaseSource;
    private final StorageSource mStorageSource;

    @Inject
    public AppRepositoryManager(AuthenticationSource authenticationSource,
                                RealtimeDatabaseSource realtimeDatabaseSource,
                                StorageSource storageSource) {
        mAuthenticationSource = authenticationSource;
        mRealtimeDatabaseSource = realtimeDatabaseSource;
        mStorageSource = storageSource;
    }

    @Override
    public Maybe<AuthResult> signInWithEmailAndPassword(FirebaseAuth firebaseAuth
            , String email, String password) {
        return mAuthenticationSource.signInWithEmailAndPassword(firebaseAuth,
                email, password);
    }

    @Override
    public Maybe<AuthResult> signInWithCredential(FirebaseAuth firebaseAuth
            , AuthCredential credential) {
        return mAuthenticationSource.signInWithCredential(firebaseAuth, credential);
    }

    @Override
    public Maybe<AuthResult> createUserWithEmailAndPassword(FirebaseAuth firebaseAuth
            , String email, String password) {
        return mAuthenticationSource.createUserWithEmailAndPassword(firebaseAuth,
                email, password);
    }

    @Override
    public Flowable<DataSnapshot> observeValueEvent(Query query, BackpressureStrategy strategy) {
        return mRealtimeDatabaseSource.observeValueEvent(query, strategy);
    }

    @Override
    public Maybe<DataSnapshot> observeSingleValueEvent(Query query) {
        return mRealtimeDatabaseSource.observeSingleValueEvent(query);
    }

    @Override
    public Single<DataSnapshot> runTransaction(DatabaseReference ref, boolean fireLocalEvents,
                                               long transactionValue) {
        return mRealtimeDatabaseSource.runTransaction(ref, fireLocalEvents, transactionValue);
    }

    @Override
    public Completable setValue(DatabaseReference ref, Object value) {
        return mRealtimeDatabaseSource.setValue(ref, value);
    }

    @Override
    public Completable updateChildren(DatabaseReference ref, Map<String, Object> updateData) {
        return mRealtimeDatabaseSource.updateChildren(ref, updateData);
    }

    @Override
    public Flowable<FirebaseChildEvent<DataSnapshot>>
    observeChildEvent(Query query, BackpressureStrategy strategy) {
        return mRealtimeDatabaseSource.observeChildEvent(query, strategy);
    }

    @Override
    public Flowable<DataSnapshot> observeMultipleSingleValueEvent(DatabaseReference... whereRefs) {
        return mRealtimeDatabaseSource.observeMultipleSingleValueEvent(whereRefs);
    }

    @Override
    public Maybe<DatabaseReference[]> requestFilteredReferenceKeys(DatabaseReference from,
                                                                   Query whereRef) {
        return mRealtimeDatabaseSource.requestFilteredReferenceKeys(from, whereRef);
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Class<T> clazz,
                                             BackpressureStrategy strategy) {
        return mRealtimeDatabaseSource.observeValueEvent(query, clazz, strategy);
    }

    @Override
    public <T> Maybe<T> observeSingleValueEvent(Query query, Class<T> clazz) {
        return mRealtimeDatabaseSource.observeSingleValueEvent(query, clazz);
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Class<T> clazz,
                                                                 BackpressureStrategy strategy) {
        return mRealtimeDatabaseSource.observeChildEvent(query, clazz, strategy);
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper, BackpressureStrategy strategy) {
        return mRealtimeDatabaseSource.observeValueEvent(query, mapper, strategy);
    }

    @Override
    public <T> Maybe<T> observeSingleValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper) {
        return mRealtimeDatabaseSource.observeSingleValueEvent(query, mapper);
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Function<
            ? super FirebaseChildEvent<DataSnapshot>, ? extends FirebaseChildEvent<T>> mapper,
                                                                 BackpressureStrategy strategy) {
        return mRealtimeDatabaseSource.observeChildEvent(query, mapper, strategy);
    }

    @Override
    public Flowable<DataSnapshot> observeValueEvent(Query query) {
        return mRealtimeDatabaseSource.observeValueEvent(query);
    }

    @Override
    public Flowable<FirebaseChildEvent<DataSnapshot>> observeChildEvent(Query query) {
        return mRealtimeDatabaseSource.observeChildEvent(query);
    }

    @Override
    public Single<DataSnapshot> runTransaction(DatabaseReference ref, long transactionValue) {
        return mRealtimeDatabaseSource.runTransaction(ref, transactionValue);
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Class<T> clazz) {
        return mRealtimeDatabaseSource.observeValueEvent(query, clazz);
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Class<T> clazz) {
        return mRealtimeDatabaseSource.observeChildEvent(query, clazz);
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper) {
        return mRealtimeDatabaseSource.observeValueEvent(query, mapper);
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Function<
            ? super FirebaseChildEvent<DataSnapshot>, ? extends FirebaseChildEvent<T>> mapper) {
        return mRealtimeDatabaseSource.observeChildEvent(query, mapper);
    }

    @Override
    public Maybe<byte[]> getBytes(StorageReference storageRef, long maxDownloadSizeBytes) {
        return mStorageSource.getBytes(storageRef, maxDownloadSizeBytes);
    }

    @Override
    public Maybe<Uri> getDownloadUrl(StorageReference storageRef) {
        return mStorageSource.getDownloadUrl(storageRef);
    }

    @Override
    public Single<FileDownloadTask.TaskSnapshot> getFile(StorageReference storageRef,
                                                         File destinationFile) {
        return mStorageSource.getFile(storageRef, destinationFile);
    }

    @Override
    public Single<FileDownloadTask.TaskSnapshot> getFile(StorageReference storageRef,
                                                         Uri destinationUri) {
        return mStorageSource.getFile(storageRef, destinationUri);
    }

    @Override
    public Maybe<StorageMetadata> getMetadata(StorageReference storageRef) {
        return mStorageSource.getMetadata(storageRef);
    }

    @Override
    public Single<StreamDownloadTask.TaskSnapshot> getStream(StorageReference storageRef) {
        return mStorageSource.getStream(storageRef);
    }

    @Override
    public Single<StreamDownloadTask.TaskSnapshot> getStream(StorageReference storageRef,
                                                             StreamDownloadTask.StreamProcessor
                                                                     processor) {
        return mStorageSource.getStream(storageRef, processor);
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putBytes(StorageReference storageRef, byte[] bytes) {
        return mStorageSource.putBytes(storageRef, bytes);
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putBytes(StorageReference storageRef, byte[] bytes,
                                                    StorageMetadata metadata) {
        return mStorageSource.putBytes(storageRef, bytes, metadata);
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri) {
        return mStorageSource.putFile(storageRef, uri);
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri,
                                                   StorageMetadata metadata) {
        return mStorageSource.putFile(storageRef, uri, metadata);
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putFile(StorageReference storageRef, Uri uri,
                                                   StorageMetadata metadata,
                                                   Uri existingUploadUri) {
        return mStorageSource.putFile(storageRef, uri, metadata, existingUploadUri);
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putStream(StorageReference storageRef,
                                                     InputStream stream, StorageMetadata metadata) {
        return mStorageSource.putStream(storageRef, stream, metadata);
    }

    @Override
    public Single<UploadTask.TaskSnapshot> putStream(StorageReference storageRef,
                                                     InputStream stream) {
        return mStorageSource.putStream(storageRef, stream);
    }

    @Override
    public Maybe<StorageMetadata> updateMetadata(StorageReference storageRef,
                                                 StorageMetadata metadata) {
        return mStorageSource.updateMetadata(storageRef, metadata);
    }

    @Override
    public Completable delete(StorageReference storageRef) {
        return mStorageSource.delete(storageRef);
    }
}
