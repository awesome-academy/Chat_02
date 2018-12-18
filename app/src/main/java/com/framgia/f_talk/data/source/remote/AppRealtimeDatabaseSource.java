package com.framgia.f_talk.data.source.remote;

import com.framgia.f_talk.data.source.remote.firebase.DataSnapshotMapper;
import com.framgia.f_talk.data.source.remote.firebase.EventType;
import com.framgia.f_talk.data.source.remote.firebase.FirebaseChildEvent;
import com.framgia.f_talk.data.source.remote.firebase.exception.FirebaseDataException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

import static com.framgia.f_talk.data.source.remote.firebase.DataSnapshotMapper.DATA_SNAPSHOT_EXISTENCE_PREDICATE;

@Singleton
public class AppRealtimeDatabaseSource implements RealtimeDatabaseSource {
    @Inject
    public AppRealtimeDatabaseSource() {
    }


    @Override
    public Flowable<DataSnapshot> observeValueEvent(Query query, BackpressureStrategy strategy) {
        return Flowable.create(emitter -> {
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    emitter.onNext(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    if (!emitter.isCancelled())
                        emitter.onError(new FirebaseDataException(error));
                }
            };
            emitter.setCancellable(() -> query.removeEventListener(valueEventListener));
            query.addValueEventListener(valueEventListener);
        }, strategy);
    }

    @Override
    public Maybe<DataSnapshot> observeSingleValueEvent(Query query) {
        return Maybe.create(emitter -> query.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            emitter.onSuccess(dataSnapshot);
                        } else {
                            emitter.onComplete();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        if (!emitter.isDisposed())
                            emitter.onError(new FirebaseDataException(error));
                    }
                }));
    }

    @Override
    public Single<DataSnapshot> runTransaction(DatabaseReference ref, boolean fireLocalEvents,
                                               long transactionValue) {
        return Single.create(emitter -> ref.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Integer currentValue = mutableData.getValue(Integer.class);
                if (currentValue == null) {
                    mutableData.setValue(transactionValue);
                } else {
                    mutableData.setValue(currentValue + transactionValue);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                if (databaseError != null && !emitter.isDisposed()) {
                    emitter.onError(new FirebaseDataException(databaseError));
                } else {
                    emitter.onSuccess(dataSnapshot);
                }
            }
        }, fireLocalEvents));
    }

    @Override
    public Completable setValue(DatabaseReference ref, Object value) {
        return Completable.create(e -> ref.setValue(value)
                .addOnSuccessListener(aVoid -> e.onComplete()).addOnFailureListener(exception -> {
                    if (!e.isDisposed())
                        e.onError(exception);
                }));
    }

    @Override
    public Completable updateChildren(DatabaseReference ref, Map<String, Object> updateData) {
        return Completable.create(emitter -> ref.updateChildren(
                updateData, (error, databaseReference) -> {
                    if (error != null && !emitter.isDisposed()) {
                        emitter.onError(new FirebaseDataException(error));
                    } else {
                        emitter.onComplete();
                    }
                }));
    }

    @Override
    public Flowable<FirebaseChildEvent<DataSnapshot>> observeChildEvent(
            Query query, BackpressureStrategy strategy) {
        return Flowable.create(emitter -> {
            ChildEventListener childEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    emitter.onNext(
                            new FirebaseChildEvent<>(dataSnapshot.getKey(),
                                    dataSnapshot, previousChildName, EventType.ADDED));
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    emitter.onNext(
                            new FirebaseChildEvent<>(dataSnapshot.getKey(),
                                    dataSnapshot, previousChildName, EventType.CHANGED));
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    emitter.onNext(new FirebaseChildEvent<>(dataSnapshot.getKey(),
                            dataSnapshot, EventType.REMOVED));
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    emitter.onNext(
                            new FirebaseChildEvent<>(dataSnapshot.getKey(),
                                    dataSnapshot, previousChildName, EventType.MOVED));
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    if (!emitter.isCancelled())
                        emitter.onError(new FirebaseDataException(error));
                }
            };
            emitter.setCancellable(() -> query.removeEventListener(childEventListener));
            query.addChildEventListener(childEventListener);

        }, strategy);
    }

    @Override
    public Flowable<DataSnapshot> observeMultipleSingleValueEvent(DatabaseReference... whereRefs) {
        return Maybe.merge(Flowable.fromArray(whereRefs)
                .map((Function<DatabaseReference, MaybeSource<? extends DataSnapshot>>)
                        this::observeSingleValueEvent)
        );
    }

    @Override
    public Maybe<DatabaseReference[]> requestFilteredReferenceKeys(DatabaseReference from,
                                                                   Query whereRef) {
        return observeSingleValueEvent(whereRef, dataSnapshot -> {
            int childrenCount = (int) dataSnapshot.getChildrenCount();
            DatabaseReference[] filterRefs = new DatabaseReference[childrenCount];
            Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
            for (int i = 0; i < childrenCount; i++) {
                filterRefs[i] = from.child(iterator.next().getKey());
            }
            return filterRefs;
        });
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Class<T> clazz,
                                             BackpressureStrategy strategy) {
        return observeValueEvent(query, DataSnapshotMapper.of(clazz), strategy);
    }

    @Override
    public <T> Maybe<T> observeSingleValueEvent(Query query, Class<T> clazz) {
        return observeSingleValueEvent(query, DataSnapshotMapper.of(clazz));
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Class<T> clazz,
                                                                 BackpressureStrategy strategy) {
        return observeChildEvent(query, DataSnapshotMapper.ofChildEvent(clazz), strategy);
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper,
                                             BackpressureStrategy strategy) {
        return observeValueEvent(query, strategy).map(mapper);
    }

    @Override
    public <T> Maybe<T> observeSingleValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper) {
        return observeSingleValueEvent(query)
                .filter(DATA_SNAPSHOT_EXISTENCE_PREDICATE)
                .map(mapper);
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Function<
            ? super FirebaseChildEvent<DataSnapshot>, ? extends FirebaseChildEvent<T>> mapper,
                                                                 BackpressureStrategy strategy) {
        return observeChildEvent(query, strategy).map(mapper);
    }

    @Override
    public Flowable<DataSnapshot> observeValueEvent(Query query) {
        return observeValueEvent(query, BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<FirebaseChildEvent<DataSnapshot>> observeChildEvent(
            Query query) {
        return observeChildEvent(query, BackpressureStrategy.DROP);
    }

    @Override
    public Single<DataSnapshot> runTransaction(DatabaseReference ref, long transactionValue) {
        return runTransaction(ref, true, transactionValue);
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Class<T> clazz) {
        return observeValueEvent(query, DataSnapshotMapper.of(clazz), BackpressureStrategy.DROP);
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Class<T> clazz) {
        return observeChildEvent(query, DataSnapshotMapper.ofChildEvent(clazz),
                BackpressureStrategy.DROP);
    }

    @Override
    public <T> Flowable<T> observeValueEvent(Query query, Function<? super DataSnapshot,
            ? extends T> mapper) {
        return observeValueEvent(query, BackpressureStrategy.DROP).map(mapper);
    }

    @Override
    public <T> Flowable<FirebaseChildEvent<T>> observeChildEvent(Query query, Function<? super
            FirebaseChildEvent<DataSnapshot>, ? extends FirebaseChildEvent<T>> mapper) {
        return observeChildEvent(query, BackpressureStrategy.DROP).map(mapper);
    }
}
