package edu.isil.proyspotify01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    private var trackList: List<Track> = emptyList()

    fun submitList(tracks: List<Track>) {
        trackList = tracks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(trackList[position])
    }

    override fun getItemCount(): Int = trackList.size

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackName: TextView = itemView.findViewById(R.id.tvTrackName)
        private val artistName: TextView = itemView.findViewById(R.id.tvArtistName)
        private val popularity: TextView = itemView.findViewById(R.id.tvPopularity)

        fun bind(track: Track) {
            trackName.text = track.name
            artistName.text = track.artists.toString()
            popularity.text = "Popularity: ${track.popularity}"
        }
    }
}
