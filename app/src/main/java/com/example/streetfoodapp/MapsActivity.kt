package com.example.streetfoodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.streetfoodapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.LatLngBounds

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val sousse = LatLng(35.82, 10.64)
        mMap.addMarker(MarkerOptions().position(sousse).title("Marker in Sousse"))


        val tunis = LatLng(36.8, 10.17)
        mMap.addMarker(MarkerOptions().position(tunis).title("Marker in Tunis"))

        // Move the camera to a position that includes both markers
        val bounds = LatLngBounds.builder().include(sousse).include(tunis).build()
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10))

        // Changer le type de vue de la carte
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID


        mMap.setOnMapClickListener { latLng ->

            val message = "Latitude: ${latLng.latitude}, Longitude: ${latLng.longitude}"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

}