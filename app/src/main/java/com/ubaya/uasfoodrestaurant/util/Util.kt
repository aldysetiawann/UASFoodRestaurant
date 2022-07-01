package com.ubaya.uasfoodrestaurant.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Database
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.uasfoodrestaurant.R
import com.ubaya.uasfoodrestaurant.model.FoodRestaurantDao
import com.ubaya.uasfoodrestaurant.model.FoodRestaurantDataBase
import java.lang.Exception

val DB_NAME = "FoodRestaurantDataBase"

fun buildDB(context: Context):FoodRestaurantDataBase {
    val db = Room.databaseBuilder(context,
        FoodRestaurantDataBase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()
    return db
}

val MIGRATION_1_2 = object: Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE myrecipes (recipe_id INTEGER PRIMARY KEY,name TEXT NOT NULL,category TEXT NOT NULL,likes INTEGER, poster TEXT NOT NULL)")
        database.execSQL("CREATE TABLE recipesdraft (recipe_id INTEGER PRIMARY KEY,name TEXT,category TEXT,likes INTEGER, poster TEXT)")
        database.execSQL("CREATE TABLE ingredients (ingredient_id INTEGER PRIMARY KEY,recipe_id_ing INTEGER,item TEXT NOT NULL, amount TEXT)")
        database.execSQL("CREATE TABLE preparations (preparation_id INTEGER PRIMARY KEY,recipe_id_prep INTEGER,step INTEGER, description TEXT NOT NULL)")
    }

}

val MIGRATION_2_3 = object: Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE recipes ADD COLUMN public_stat INTEGER DEFAULT 0 NOT NULL")
    }
}

fun createNotificationChannel(context: Context, importance:Int, showBadge:Boolean, name:String, description:String){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

fun ImageView.loadImage(url:String, progressBar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }

        })
}

@BindingAdapter("loadWithPicasso")
fun loadWithPicasso(imageView2: ImageView, imageUrl: String? ) {
    Picasso.get()
        .load("https://cms-revamp.daihatsu.co.id/uploads/tipsandtrick/25-makanan-khas-palembang-1611581901437.jpeg")
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(imageView2, object: Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {

            }

        })
}
