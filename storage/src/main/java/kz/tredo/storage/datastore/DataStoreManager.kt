package kz.tredo.storage.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(
    private val context: Context,
    storeName: String = "DEFAULT_STORE_NAME"
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = storeName)

    suspend fun <T> write(data: T, key: Preferences.Key<T>) {
        context.dataStore.edit { preference ->
            preference[key] = data
        }
    }

    suspend fun <T> read(key: Preferences.Key<T>): Flow<T?> =
        context.dataStore.data.map { preference ->
            preference[key]
        }
}