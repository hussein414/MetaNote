package com.example.meta.ui.fragment.note

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.meta.R
import com.example.meta.`data`.model.NoteModel
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class HomeNoteFragmentDirections private constructor() {
  private data class ActionHomeFragmentToUpdateNoteFragment(
    public val note: NoteModel?
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_updateNoteFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
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
  }

  public companion object {
    public fun actionHomeFragmentToNewNoteFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_newNoteFragment)

    public fun actionHomeFragmentToUpdateNoteFragment(note: NoteModel?): NavDirections =
        ActionHomeFragmentToUpdateNoteFragment(note)
  }
}
