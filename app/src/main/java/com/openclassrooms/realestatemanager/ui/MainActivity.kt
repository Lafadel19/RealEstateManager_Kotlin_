package com.openclassrooms.realestatemanager.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.openclassrooms.realestatemanager.data.database.AppDatabase
import com.openclassrooms.realestatemanager.data.repository.RealEstateRepository
import com.openclassrooms.realestatemanager.ui.theme.RealEstateManagerTheme
import com.openclassrooms.realestatemanager.ui.viewmodels.AddRealEstateViewModel
import com.openclassrooms.realestatemanager.ui.viewmodels.DetailViewModel
import com.openclassrooms.realestatemanager.ui.viewmodels.MainViewModel
import com.openclassrooms.realestatemanager.ui.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var addViewModel: AddRealEstateViewModel
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = RealEstateRepository(AppDatabase.getDatabase(applicationContext).realEstateDao())
        val factory = ViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        addViewModel = ViewModelProvider(this, factory)[AddRealEstateViewModel::class.java]
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        setContent {
            RealEstateManagerTheme {
                MainApp(
                    mainViewModel = mainViewModel,
                    addViewModel = addViewModel,
                    detailViewModel = detailViewModel
                )
            }
        }
    }
}
