package com.anki.offlinelisting.remote;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;

import static com.anki.offlinelisting.remote.Status.ERROR;
import static com.anki.offlinelisting.remote.Status.LOADING;

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
public class Resource<T> extends BaseObservable {
    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable private final String message;


    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}