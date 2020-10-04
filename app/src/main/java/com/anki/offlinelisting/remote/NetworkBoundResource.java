package com.anki.offlinelisting.remote;

import android.annotation.SuppressLint;

import android.os.AsyncTask;


import com.anki.offlinelisting.utils.CustomMediatorLiveData;
import com.google.gson.stream.MalformedJsonException;


import java.io.IOException;
import java.net.SocketTimeoutException;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * This class act as the decider to cache the response/ fetch from the service always
 */
public abstract class NetworkBoundResource<T, V> {
    private final CustomMediatorLiveData<Resource<T>> result = new CustomMediatorLiveData<>();
    public boolean shouldFetchData = true;
    public boolean pullToRefresh = false;

    @MainThread
    protected NetworkBoundResource(boolean refresh) {
        pullToRefresh = refresh;
        result.setValue(Resource.loading(null));
        // Always load the data from DB intially so that we have
        LiveData<T> dbSource = loadFromDb(pullToRefresh);
        // Fetch the data from network and add it to the resource
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(shouldFetchData)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> {
                    if(null != newData)
                        result.setValue(Resource.success(newData)) ;
                });
            }
        });
    }

    /**
     * This method fetches the data from remote service and save it to local db
     * @param dbSource - Database source
     */
    private void fetchFromNetwork(final LiveData<T> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        createCall().enqueue(new Callback<V>() {
            @Override
            public void onResponse(@NonNull Call<V> call, @NonNull Response<V> response) {
                result.removeSource(dbSource);
                saveResultAndReInit(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<V> call, @NonNull Throwable t) {
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(getCustomErrorMessage(t), newData)));
            }
        });
    }

    private String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return "Oooops! We couldn’t capture your request in time. Please try again.";
        } else if (error instanceof MalformedJsonException) {
            return  "Oops! We hit an error. Try again later";
        } else if (error instanceof IOException) {
             return  "Oh! You are not connected to a wifi or cellular data network. Please connect and try again";
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return "Something went wrong!";
        }

    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    private void saveResultAndReInit(V response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                   result.addSource(loadFromDb(pullToRefresh), newData -> {
                    if (null != newData)
                        result.setValue(Resource.success(newData));
                  });
            }
        }.execute();
    }

    @WorkerThread
    protected abstract void saveCallResult(V item);

    @MainThread
    public boolean shouldFetch(boolean shouldFetchData) {
        return shouldFetchData;
    }

    @NonNull
    @MainThread
    protected abstract LiveData<T> loadFromDb(boolean pullToRefresh);

    @NonNull
    @MainThread
    protected abstract Call<V> createCall();

    public final MediatorLiveData<Resource<T>> getAsLiveData() {
        return result;
    }
}
