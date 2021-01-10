package es.uva.inf.clientetwitter.auxiliar

import android.database.Cursor
import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import android.widget.SimpleCursorAdapter
import es.uva.inf.clientetwitter.R

object TimeStampConverter : SimpleCursorAdapter.ViewBinder {
    override fun setViewValue(view: View?, cursor: Cursor?, columnIndex: Int): Boolean {
        if(view?.id != R.id.item_created_at) {
            return false
        }

        val timestamp = cursor!!.getLong(columnIndex)
        val relativeTime = DateUtils.getRelativeTimeSpanString(timestamp)
        (view as TextView).text = relativeTime

        return true
    }
}