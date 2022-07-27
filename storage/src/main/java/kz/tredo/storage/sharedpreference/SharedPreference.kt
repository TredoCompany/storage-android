package kz.tredo.storage.sharedpreference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * хелпер для работы с SharedPref
 */
const val NO_BACKUP_SHARED_PREF = "NO_BACKUP_SHARED_PREF"
const val BACKUP_SHARED_PREF = "BACKUP_SHARED_PREF"

/**
 * Для того, чтобы работали NO_BACKUP_SHARED_PREF, необходимо:
 * Создать файл backup_scheme.xml, в котором
 *
 * <?xml version="1.0" encoding="utf-8"?>
 * <full-backup-content>
 *      <exclude domain="sharedpref" path="NO_BACKUP_SHARED_PREF.xml" />
 * </full-backup-content>
 *
 * Добавить в манифест
 *      android:fullBackupContent="@xml/backup_scheme"
 * */
class SharedPreference(private val context: Context) {

    private val sharedPreferencesEditorMap =
        mutableMapOf<SharedPreferences, SharedPreferences.Editor>()

    fun getBoolean(
        key: String,
        defaultValue: Boolean = EMPTY_BOOLEAN_SETTING,
        prefName: String = EMPTY_STRING
    ) = getBoolean(getSharedPreferences(context, prefName), key, defaultValue)

    fun getString(
        key: String,
        defaultValue: String = EMPTY_STRING,
        prefName: String = EMPTY_STRING
    ) = getString(getSharedPreferences(context, prefName), key, defaultValue)

    fun getStringSet(
        key: String,
        defaultValue: Set<String> = EMPTY_SET_SETTING,
        prefName: String = EMPTY_STRING
    ) = getStringSet(getSharedPreferences(context, prefName), key, defaultValue)

    fun getInt(
        key: String,
        defaultValue: Int = Companion.EMPTY_INT_SETTING,
        prefName: String = EMPTY_STRING
    ) = getInt(getSharedPreferences(context, prefName, defaultValue), key)

    fun getLong(
        key: String,
        defaultValue: Long = EMPTY_LONG_SETTING,
        prefName: String = EMPTY_STRING
    ) = getLong(getSharedPreferences(context, prefName), key, defaultValue)

    fun getFloat(
        key: String,
        defaultValue: Float = EMPTY_FLOAT_SETTING,
        prefName: String = EMPTY_STRING
    ) = getFloat(getSharedPreferences(context, prefName), key, defaultValue)

    fun getDouble(
        key: String,
        defaultValue: Double = EMPTY_DOUBLE_SETTING,
        prefName: String = EMPTY_STRING
    ) = getDouble(getSharedPreferences(context, prefName), key, defaultValue)

    fun putBoolean(
        key: String,
        value: Boolean,
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        putBoolean(getSharedPreferences(context, prefName), key, value, async)
    }

    fun putString(
        key: String,
        value: String,
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        putString(getSharedPreferences(context, prefName), key, value, async)
    }

    fun putInt(
        key: String,
        value: Int,
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        putInt(getSharedPreferences(context, prefName), key, value, async)
    }

    fun putLong(
        key: String,
        value: Long,
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        putLong(getSharedPreferences(context, prefName), key, value, async)
    }

    fun putFloat(
        key: String,
        value: Float,
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        putFloat(getSharedPreferences(context, prefName), key, value, async)
    }

    fun putDouble(
        key: String,
        value: Double,
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        putDouble(getSharedPreferences(context, prefName), key, value, async)
    }

    fun removeKey(
        key: String,
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        removeKey(getSharedPreferences(context, prefName), key, async)
    }

    fun clear(
        prefName: String = EMPTY_STRING,
        async: Boolean = true
    ) {
        clear(getSharedPreferences(context, prefName), async)
    }

    private fun getBoolean(
        sp: SharedPreferences,
        key: String,
        defaultValue: Boolean = Companion.EMPTY_BOOLEAN_SETTING
    ) = sp.getBoolean(key, defaultValue)

    private fun getString(
        sp: SharedPreferences,
        key: String,
        defaultValue: String = EMPTY_STRING
    ) = sp.getString(key, defaultValue) ?: EMPTY_STRING

    private fun getStringSet(
        sp: SharedPreferences,
        key: String,
        defaultValue: Set<String> = EMPTY_SET_SETTING
    ): MutableSet<String> = sp.getStringSet(key, defaultValue) ?: mutableSetOf()

    private fun getInt(
        sp: SharedPreferences,
        key: String,
        defaultValue: Int = Companion.EMPTY_INT_SETTING
    ) = sp.getInt(key, defaultValue)

    private fun getLong(
        sp: SharedPreferences,
        key: String,
        defaultValue: Long = EMPTY_LONG_SETTING
    ) = sp.getLong(key, defaultValue)

    private fun getFloat(
        sp: SharedPreferences,
        key: String,
        defaultValue: Float = EMPTY_FLOAT_SETTING
    ) = sp.getFloat(key, defaultValue)

    private fun getDouble(
        sp: SharedPreferences,
        key: String,
        defaultValue: Double = EMPTY_DOUBLE_SETTING
    ) = Double.fromBits(sp.getLong(key, defaultValue.toBits()))

    private fun putBoolean(
        sp: SharedPreferences,
        key: String,
        value: Boolean,
        async: Boolean = true
    ) {
        val editor = getOrCreateEditor(sp)
        editor.putBoolean(key, value)
        saveChanges(editor, async)
    }

    private fun putString(
        sp: SharedPreferences,
        key: String,
        value: String,
        async: Boolean = true
    ) {
        val editor = getOrCreateEditor(sp)
        editor.putString(key, value)
        saveChanges(editor, async)
    }

    private fun putStringSet(
        sp: SharedPreferences,
        key: String,
        value: Set<String>,
        async: Boolean = true
    ) {
        val editor = getOrCreateEditor(sp)
        editor.putStringSet(key, value)
        saveChanges(editor, async)
    }

    private fun putInt(
        sp: SharedPreferences,
        key: String,
        value: Int,
        async: Boolean = true
    ) {
        val editor = getOrCreateEditor(sp)
        editor.putInt(key, value)
        saveChanges(editor, async)
    }

    private fun putLong(
        sp: SharedPreferences,
        key: String,
        value: Long,
        async: Boolean = true
    ) {
        val editor = getOrCreateEditor(sp)
        editor.putLong(key, value)
        saveChanges(editor, async)
    }

    private fun putFloat(
        sp: SharedPreferences,
        key: String,
        value: Float,
        async: Boolean = true
    ) {
        val editor = getOrCreateEditor(sp)
        editor.putFloat(key, value)
        saveChanges(editor, async)
    }

    private fun putDouble(
        sp: SharedPreferences,
        key: String,
        value: Double,
        async: Boolean = true
    ) {
        val editor = getOrCreateEditor(sp)
        editor.putLong(key, value.toBits())
        saveChanges(editor, async)
    }

    private fun removeKey(sp: SharedPreferences, key: String, async: Boolean = true) {
        val editor = getOrCreateEditor(sp)
        editor.remove(key)
        saveChanges(editor, async)
    }

    private fun clear(sp: SharedPreferences, async: Boolean = true) {
        val editor = getOrCreateEditor(sp)
        editor.clear()
        saveChanges(editor, async)
    }

    private fun getSharedPreferences(
        context: Context,
        name: String,
        mode: Int = Context.MODE_PRIVATE
    ): SharedPreferences =
        if (name.isEmpty()) {
            getDefaultSharedPreferences(context)
        } else {
            context.getSharedPreferences(name, mode)
        }

    @SuppressLint("CommitPrefEdits")
    private fun getOrCreateEditor(sp: SharedPreferences): SharedPreferences.Editor =
        sharedPreferencesEditorMap.getOrPut(sp) {
            sp.edit()
        }

    private fun saveChanges(editor: SharedPreferences.Editor, async: Boolean = true) {
        if (async) {
            editor.apply()
        } else {
            editor.commit()
        }
    }

    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    companion object {
        const val EMPTY_INT_SETTING = -1
        private const val EMPTY_BOOLEAN_SETTING = false
        private const val EMPTY_LONG_SETTING = -1L
        private const val EMPTY_FLOAT_SETTING = -1f
        private const val EMPTY_DOUBLE_SETTING = -1.0
        private const val EMPTY_STRING = ""
        private val EMPTY_SET_SETTING = HashSet<String>()
    }
}