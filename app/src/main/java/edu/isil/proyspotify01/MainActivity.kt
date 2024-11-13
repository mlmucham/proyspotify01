package edu.isil.proyspotify01

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val accessToken = "Bearer BQDBPfsJi_P2_0uQoBWLXXsHvvJ52LhDuhOQJKJwynvwS_mNb2JnVGrxuZlfTbXrMfjjOyWme3s5ax8EOo3dKF-bcRyKds7TdQOpCgQRlbre-uRZlkQ"

    // Declaración de RecyclerView y Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa el RecyclerView y el Adapter
        recyclerView = findViewById(R.id.recyclerViewTracks)
        adapter = TrackAdapter() // Asegúrate de haber creado un adaptador TrackAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Llamada para obtener el top 10 de pistas
        fetchTopTracks()
    }

    private fun fetchTopTracks() {
        val spotifyApi = SpotifyApiClient.instance

        // Paso 1: Buscar la playlist "Top 50 - Global"
        spotifyApi.searchPlaylist(accessToken, "Top 50 - Global").enqueue(object : Callback<PlaylistSearchResponse> {
            override fun onResponse(call: Call<PlaylistSearchResponse>, response: Response<PlaylistSearchResponse>) {
                if (response.isSuccessful) {
                    val playlistId = response.body()?.playlists?.items?.firstOrNull()?.id
                    if (playlistId != null) {
                        // Paso 2: Obtener las primeras 10 pistas de la playlist
                        getPlaylistTracks(playlistId)
                    } else {
                        Log.e("SpotifyAPI", "Playlist no encontrada")
                    }
                } else {
                    Log.e("SpotifyAPI", "Error en búsqueda de playlist")
                }
            }

            override fun onFailure(call: Call<PlaylistSearchResponse>, t: Throwable) {
                Log.e("SpotifyAPI", "Error en la llamada API: ${t.message}")
            }
        })
    }

    private fun getPlaylistTracks(playlistId: String) {
        val spotifyApi = SpotifyApiClient.instance

        // Obtener las pistas de la playlist, limitando los resultados a 10
        spotifyApi.getPlaylistTracks(accessToken, playlistId).enqueue(object : Callback<TracksResponse> {
            override fun onResponse(call: Call<TracksResponse>, response: Response<TracksResponse>) {
                if (response.isSuccessful) {
                    // Limitar la lista de canciones a las primeras 10
                    val topTracks: List<Track> = response.body()?.items?.take(10)?.map { it.track } ?: emptyList()
                    adapter.submitList(topTracks)

                } else {
                    Log.e("SpotifyAPI", "Error al obtener pistas de la playlist")
                }
            }

            override fun onFailure(call: Call<TracksResponse>, t: Throwable) {
                Log.e("SpotifyAPI", "Error en la llamada API para obtener pistas: ${t.message}")
            }
        })
    }
}
