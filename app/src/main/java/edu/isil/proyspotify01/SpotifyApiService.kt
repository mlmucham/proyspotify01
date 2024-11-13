package edu.isil.proyspotify01

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Path

interface SpotifyApiService {
    @GET("v1/search")
    fun searchPlaylist(
        @Header("Authorization") accessToken: String,
        @Query("q") query: String,
        @Query("type") type: String = "playlist",
        @Query("limit") limit: Int = 1
    ): Call<PlaylistSearchResponse>

    @GET("v1/playlists/{playlist_id}/tracks")
    fun getPlaylistTracks(
        @Header("Authorization") accessToken: String,
        @Path("playlist_id") playlistId: String
    ): Call<TracksResponse>
}
