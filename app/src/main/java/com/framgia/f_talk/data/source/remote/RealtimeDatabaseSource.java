package com.framgia.f_talk.data.source.remote;


import com.framgia.f_talk.data.source.remote.firebase.FirebaseChildEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public interface RealtimeDatabaseSource {
    Flowable<DataSnapshot> observeValueEvent(Query query, BackpressureStrategy strategy);

    Maybe<DataSnapshot> observeSingleValueEvent(Query query);

    Single<DataSnapshot> runTransaction(DatabaseReference ref, boolean fireLocalEvents,
                                        long transactionValue);

    Completable setValue(DatabaseReference ref, Object value);

    Completable updateChildren(DatabaseReference ref, Map<String, Object> updateData);

    Flowable<FirebaseChildEvent<DataSnapshot>> observeChildEvent(Query query,
                                                                 BackpressureStrategy strategy);

    Flowable<DataSnapshot> observeMultipleSingleValueEvent(DatabaseReference... whereRefs);

    Maybe<DatabaseReference[]> requestFilteredReferenceKeys(DatabaseReference from, Query whereRef);

    <T> Flowable<T> observeValueEvent(Query query, Class<T> clazz, BackpressureStrategy strategy);

    <T> Maybe<T> observeSingleValueEvent(Query query, Class<T> clazz);

    <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Class<T> clazz,
                                                          BackpressureStrategy strategy);

    <T> Flowable<T> observeValueEvent(Query query,
                                      Function<? super DataSnapshot, ? extends T> mapper,
                                      BackpressureStrategy strategy);

    <T> Maybe<T> observeSingleValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper);

    <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Function<? super
            FirebaseChildEvent<DataSnapshot>, ? extends FirebaseChildEvent<T>> mapper,
                                                          BackpressureStrategy strategy);

    Flowable<DataSnapshot> observeValueEvent(Query query);

    Flowable<FirebaseChildEvent<DataSnapshot>> observeChildEvent(Query query);

    Single<DataSnapshot> runTransaction(DatabaseReference ref, long transactionValue);

    <T> Flowable<T> observeValueEvent(Query query, Class<T> clazz);

    <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Class<T> clazz);

    <T> Flowable<T> observeValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper);

    <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Function<? super
            FirebaseChildEvent<DataSnapshot>, ? extends FirebaseChildEvent<T>> mapper);
}
