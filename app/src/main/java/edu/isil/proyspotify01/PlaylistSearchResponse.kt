package edu.isil.proyspotify01

// Modelo para la respuesta de búsqueda de la lista de reproducción
data class PlaylistSearchResponse(
    val playlists: Playlists
)

data class Playlists(
    val items: List<PlaylistItem>
)

data class PlaylistItem(
    val id: String,
    val name: String
)

// Modelo para la respuesta de las pistas de la lista de reproducción
data class TracksResponse(
    val items: List<TrackItem>
)

data class TrackItem(
    val track: Track
)

data class Track(
    val name: String,
    val artists: List<Artist>,
    val popularity: Int
)

data class Artist(
    val name: String
)

