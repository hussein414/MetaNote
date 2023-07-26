package com.example.meta.ui.fragment.note

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.example.meta.`data`.model.NoteModel
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class UpdateNoteFragmentArgs(
  public val note: NoteModel?
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(NoteModel::class.java)) {
      result.putParcelable("note", this.note as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(NoteModel::class.java)) {
      result.putSerializable("note", this.note as Serializable?)
    } else {
      throw UnsupportedOperationException(NoteModel::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(NoteModel::class.java)) {
      result.set("note", this.note as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(NoteModel::class.java)) {
      result.set("note", this.note as Serializable?)
    } else {
      throw UnsupportedOperationException(NoteModel::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): UpdateNoteFragmentArgs {
      bundle.setClassLoader(UpdateNoteFragmentArgs::class.java.classLoader)
      val __note : NoteModel?
      if (bundle.containsKey("note")) {
        if (Parcelable::class.java.isAssignableFrom(NoteModel::class.java) ||
            Serializable::class.java.isAssignableFrom(NoteModel::class.java)) {
          __note = bundle.get("note") as NoteModel?
        } else {
          throw UnsupportedOperationException(NoteModel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"note\" is missing and does not have an android:defaultValue")
      }
      return UpdateNoteFragmentArgs(__note)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): UpdateNoteFragmentArgs {
      val __note : NoteModel?
      if (savedStateHandle.contains("note")) {
        if (Parcelable::class.java.isAssignableFrom(NoteModel::class.java) ||
            Serializable::class.java.isAssignableFrom(NoteModel::class.java)) {
          __note = savedStateHandle.get<NoteModel?>("note")
        } else {
          throw UnsupportedOperationException(NoteModel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"note\" is missing and does not have an android:defaultValue")
      }
      return UpdateNoteFragmentArgs(__note)
    }
  }
}
